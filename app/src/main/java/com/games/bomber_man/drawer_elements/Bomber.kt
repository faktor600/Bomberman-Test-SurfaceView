package com.games.bomber_man.drawer_elements

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import androidx.core.content.res.ResourcesCompat
import com.games.bomber_man.R
import com.games.bomber_man.game_engine.LivingObject

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

    private var isStartGame = true
    private var currentAnimation = 4

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
        if(isStartGame){
            canvas.drawBitmap(
                animationList[currentAnimation],
                null,
                Rect(
                    widthTitle * 1,
                    heightTitle * 1 + heightField,
                    widthTitle * 2,
                    heightTitle * 2 + heightField
                ),
                null
            )
        }else{
            val bomberCoordinates = findBomberMan()
            canvas.drawBitmap(
                animationList[4],
                null,
                Rect(
                    widthTitle * bomberCoordinates[1],
                    heightTitle * bomberCoordinates[0] + heightField,
                    widthTitle * (bomberCoordinates[1] + 1),
                    heightTitle * (bomberCoordinates[0] + 1) + heightField
                ),
                null
            )
        }
    }

    private fun findBomberMan(): Array<Int>{
        for(i in 1..12){
            for(j in 1..30){
                if(field[i][j] == 9){
                    return arrayOf(i, j)
                }
            }
        }
        return arrayOf(0, 0)
    }
}