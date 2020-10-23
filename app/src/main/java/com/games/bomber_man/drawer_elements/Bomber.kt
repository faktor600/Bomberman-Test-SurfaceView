package com.games.bomber_man.drawer_elements

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import androidx.core.content.res.ResourcesCompat
import com.games.bomber_man.R
import com.games.bomber_man.game_engine.LivingObject
import com.games.bomber_man.utils.FindBomber

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

    override var isAlive: Boolean = true

    private var animationList = ArrayList<Bitmap>()

    private var currentAnimation = 4
    private var action = ""
    private var isMoving = false

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
                "UP" -> {
                    if(currentAnimation < 11 || currentAnimation == 9){
                        currentAnimation++
                    }else if(currentAnimation !in 9..11){
                        currentAnimation = 9
                    }else if(currentAnimation == 11 || currentAnimation > 9){
                        currentAnimation--
                    }
                }
                "DOWN" -> {
                    if(currentAnimation in 3..5 && currentAnimation != 5){
                        currentAnimation++
                    }else if(currentAnimation == 5 || currentAnimation !in 3..5){
                        currentAnimation = 3
                    }
                }
                "RIGHT" -> {
                    if(currentAnimation in 6..8 && currentAnimation != 8){
                        currentAnimation++
                    }else if(currentAnimation == 8 || currentAnimation !in 6..8){
                        currentAnimation = 6
                    }
                }
                "LEFT" -> {
                    if(currentAnimation in 0..2 && currentAnimation != 2){
                        currentAnimation++
                    }else if(currentAnimation == 2 || currentAnimation !in 0..2){
                        currentAnimation = 0
                    }
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

    fun setAction(action: String){
        this.action = action
    }

    fun isBomberMoving(moving: Boolean){
        isMoving = moving
    }
}