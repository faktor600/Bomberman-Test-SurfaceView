package com.games.bomber_man.drawer_elements

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import com.games.bomber_man.game_engine.LivingObject
import com.games.bomber_man.utils.Move

class Bomber(

    private val widthField: Int,
    private val heightField: Int,
    private val widthTitle: Int,
    private val heightTitle: Int,
    private val field: Array<IntArray>,
    private val bitmapBomber: Bitmap,
    private val bitmapDeath: Bitmap,
    private val resources: Resources

) : LivingObject() {

    private val MAX_TIME_STATE = 2

    override var isAlive: Boolean = true

    private var animationList = ArrayList<Bitmap>()

    private var currentAnimation = 4
    private var isForward = true
    private var action: Move = Move.NO_MOVE
    private var isMoving = false
    private var animationTimeState = 0

    private var rect: Rect = Rect(
        widthTitle * 1,
        heightTitle * 1 + heightField,
        widthTitle * 2,
        heightTitle * 2 + heightField
    )

    init {
        val widthOneAnimation = bitmapBomber.width / 6
        val heightOneAnimation = bitmapBomber.height / 2
        for(i in 0..1){
            for(j in 0..5){
                val bitmap = Bitmap.createBitmap(
                    bitmapBomber,
                    widthOneAnimation * j,
                    heightOneAnimation * i,
                    widthOneAnimation,
                    heightOneAnimation
                )
                animationList.add(bitmap)
            }
        }
    }

    override fun moveObject(canvas: Canvas) {
        if(isMoving){
            when(action){
                Move.UP -> {
                    moveAnimation(arrayOf(9, 10, 11))
                }
                Move.DOWN -> {
                    moveAnimation(arrayOf(3, 4, 5))
                }
                Move.RIGHT -> {
                    moveAnimation(arrayOf(6, 7, 8))
                }
                Move.LEFT -> {
                    moveAnimation(arrayOf(0, 1, 2))
                }
            }
        }
        canvas.drawBitmap(
            animationList[currentAnimation],
            null,
            rect,
            null
        )
    }

    fun setBomberRect(rect: Rect){
        this.rect = rect
    }

    fun setAction(action: Move){
        this.action = action
    }

    fun isBomberMoving(moving: Boolean){
        isMoving = moving
    }

    private fun moveAnimation(array: Array<Int>){
        if(currentAnimation == array[1] && isForward && animationTimeState == MAX_TIME_STATE){
            currentAnimation++
            animationTimeState = 0
        }else if(currentAnimation == array[2] && animationTimeState == MAX_TIME_STATE){
            isForward = false
            currentAnimation --
            animationTimeState = 0
        }else if(currentAnimation == array[0] && animationTimeState == MAX_TIME_STATE){
            isForward = true
            currentAnimation++
            animationTimeState = 0
        }else if(currentAnimation == array[1] && !isForward && animationTimeState == MAX_TIME_STATE){
            currentAnimation--
            animationTimeState = 0
        }else if(currentAnimation !in array){
            currentAnimation = array[0]
            animationTimeState = 0
        }else{
            animationTimeState++
        }
    }
}