package com.games.bomber_man.utils

import android.view.MotionEvent
import android.view.View

class OnTouchEventGame(
    heightScreen: Int,
    private val widthScreen: Int,
    private val inputObserver: InputObserver
) : View.OnTouchListener {

    private val heightHalf = heightScreen / 2
    private val heightNinth = heightScreen / 9

    private val widthLeftUpDown = widthScreen / 10
    private val widthRightUpDown = widthLeftUpDown + widthScreen/13

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                checkMoveState(event.x.toInt(), event.y.toInt())
            }
            MotionEvent.ACTION_MOVE -> {
                checkMoveState(event.x.toInt(), event.y.toInt())
            }
            MotionEvent.ACTION_UP -> {
                inputObserver.isButtonTouched(false, Move.NO_MOVE)
            }
        }
        return true
    }

    private fun checkMoveState(moveX: Int, moveY: Int){
        when(moveX){
            in widthLeftUpDown..widthRightUpDown -> {
                when (moveY) {
                    in heightHalf..heightHalf + heightNinth ->{
                        inputObserver.isButtonTouched(true, Move.UP)
                    }
                    in heightHalf + 2 * heightNinth..heightHalf + 3 * heightNinth -> {
                        inputObserver.isButtonTouched(true, Move.DOWN)
                    }
                    else -> inputObserver.isButtonTouched(false, Move.NO_MOVE)
                }
            }
            in widthLeftUpDown - heightNinth..widthLeftUpDown ->{
                inputObserver.isButtonTouched(true, Move.LEFT)
            }
            in widthRightUpDown..widthLeftUpDown + widthScreen / 13 + heightNinth ->{
                inputObserver.isButtonTouched(true, Move.RIGHT)
            }
            else -> inputObserver.isButtonTouched(false, Move.NO_MOVE)
        }
    }
}