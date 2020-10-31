package com.games.bomber_man.drawer_elements

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import com.games.bomber_man.utils.DrawOnCanvas
import kotlin.random.Random

class BreakableWall(
    private val widthField: Int,
    private val heightField: Int,
    private val widthWall: Int,
    private val heightWall: Int,
    private val wallBitmap: Bitmap,
    private val animationBitmap: Bitmap,
    private val field: Array<IntArray>,
    resources: Resources,
    private val level: Int
) : DrawOnCanvas {

    private var arrayStaticWalls: ArrayList<Rect> = ArrayList()
    private val sizeArray = 30

    private lateinit var breakableWall: Bitmap
    private val animation: ArrayList<Bitmap> = ArrayList()

    init {
        setBreakableWalls()

        val width = animationBitmap.width / 6
        val height = animationBitmap.height
        for(i in 1..6){
            animation.add(
                Bitmap.createBitmap(animationBitmap, width * (i - 1), 0, width, height)
            )
        }
    }

    override fun draw(canvas: Canvas) {
        var count = 0
        for(i in 0..12){
            for(j in 0..30){
                if(field[i][j] == 2){
                    canvas.drawBitmap(
                        wallBitmap,
                        null,
                        arrayStaticWalls[count++],
                        null
                    )
                }
            }
        }
    }

    private fun setBreakableWalls(){
        var size = sizeArray

        while (size != 0){
            val x = Random.nextInt(2, 30)
            val y = Random.nextInt(2, 12)
            if(field[y][x] != 1 && field[y][x] != 2){
                field[y][x] = 2
                size--
                arrayStaticWalls.add(
                    Rect(
                        widthWall * x,
                        heightField + heightWall * y,
                        widthWall * (x + 1),
                        heightField + heightWall * (y + 1)
                    )
                )
            }
        }
    }

    fun setNewWallsRect(velocity: Int){
        if(arrayStaticWalls.size != 0 && velocity != 0){
            arrayStaticWalls.clear()
        }
        for(i in 0..12){
            for(j in 0..30){
                if(field[i][j] == 2) {
                    arrayStaticWalls.add(
                        Rect(
                            widthWall * j + velocity,
                            heightField + heightWall * i,
                            widthWall * (j + 1) + velocity,
                            heightField + heightWall * (i + 1)
                        )
                    )
                }
            }
        }
    }
}