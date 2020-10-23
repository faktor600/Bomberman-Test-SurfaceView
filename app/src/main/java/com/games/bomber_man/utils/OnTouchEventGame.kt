package com.games.bomber_man.utils

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class OnTouchEventGame(
    heightScreen: Int,
    private val widthScreen: Int,
    private val gameInputObserver: GameInputObserver
) : View.OnTouchListener {

    private val heightHalf = heightScreen / 2
    private val heightNinth = heightScreen / 9

    private val widthLeftUpDown = widthScreen / 10
    private val widthRightUpDown = widthLeftUpDown + widthScreen/13

    private var isBomberAlive = true
    private var isButtonPressed = false

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        if(event?.action == MotionEvent.ACTION_DOWN){
            isButtonPressed = true
            val moveX = event.x.toInt()
            val moveY = event.y.toInt()

            when(moveX){
                in widthLeftUpDown..widthRightUpDown -> {
                    when (moveY) {
                        in heightHalf..heightHalf + heightNinth ->{
                            gameInputObserver.isButtonTouched(true, "UP")
                        }
                        in heightHalf + 2 * heightNinth..heightHalf + 3 * heightNinth -> {
                            gameInputObserver.isButtonTouched(true, "DOWN")
                        }
                    }
                }
                in widthLeftUpDown - heightNinth..widthLeftUpDown ->{
                    gameInputObserver.isButtonTouched(true, "LEFT")
                }
                in widthRightUpDown..widthLeftUpDown + widthScreen / 13 + heightNinth ->{
                    gameInputObserver.isButtonTouched(true, "RIGHT")
                }
            }
        }else if(event?.action == MotionEvent.ACTION_UP){
            gameInputObserver.isButtonTouched(false, "")
        }
        return true
    }

    fun isBomberAlive(isBomberAlive: Boolean){
        this.isBomberAlive = isBomberAlive
    }
}