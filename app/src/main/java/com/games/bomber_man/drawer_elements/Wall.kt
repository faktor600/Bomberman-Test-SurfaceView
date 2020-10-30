package com.games.bomber_man.drawer_elements

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.res.ResourcesCompat
import com.games.bomber_man.R
import com.games.bomber_man.utils.DrawOnCanvas

class Wall(
    private val widthField: Int,
    private val heightField: Int,
    private val widthWall: Int,
    private val heightWall: Int,
    private val wallBitmap: Bitmap,
    private val field: Array<IntArray>,
    resources: Resources
) : DrawOnCanvas {

    private val paint: Paint = Paint()
    private var arrayStaticWalls: ArrayList<Rect> = ArrayList()

    init {
        paint.color = ResourcesCompat.getColor(resources, R.color.fieldColor, null)
        setNewWallsRect(0)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRect(
            0F,
            heightField.toFloat(),
            widthField.toFloat(),
            (heightField * 13).toFloat(),
            paint
        )

        var count = 0

        for(i in 0..12){
            for(j in 0..30){
                if(field[i][j] == 1){
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

    fun setNewWallsRect(velocity: Int){
        if(arrayStaticWalls.size != 0 && velocity != 0){
            arrayStaticWalls.clear()
        }
        for(i in 0..12){
            for(j in 0..30){
                if(field[i][j] == 1) {
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