package com.games.bomber_man.drawer_elements

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Rect
import androidx.core.graphics.drawable.toBitmap
import com.games.bomber_man.R
import com.games.bomber_man.utils.DrawOnCanvas

class Arrows(
    widthScreen: Int,
    heightScreen: Int,
    resources: Resources
) : DrawOnCanvas {

    private val heightHalf = heightScreen / 2
    private val heightNinth = heightScreen / 9

    private val widthLeftUpDown = widthScreen / 10
    private val widthRightUpDown = widthLeftUpDown + widthScreen/13
    private val heightTopLeftRight = heightHalf + heightNinth
    private val heightBottomLeftRight = heightTopLeftRight + 2 * heightNinth / 2

    private val rectUp: Rect = Rect(
        widthLeftUpDown,
        heightHalf,
        widthRightUpDown,
        heightHalf + heightNinth
    )
    private val rectDown: Rect = Rect(
        widthLeftUpDown,
        heightHalf + 2 * heightNinth,
        widthRightUpDown,
        heightHalf + 3 * heightNinth
    )
    private val rectLeft: Rect = Rect(
        widthLeftUpDown - heightNinth,
        heightTopLeftRight,
        widthLeftUpDown,
        heightBottomLeftRight
    )
    private val rectRight: Rect = Rect(
        widthRightUpDown,
        heightTopLeftRight,
        widthLeftUpDown + widthScreen / 13 + heightNinth,
        heightBottomLeftRight
    )

    private val matrixDown = Matrix()
    private val matrixLeft = Matrix()
    private val matrixRight = Matrix()

    private val bitmap = resources.getDrawable(R.drawable.pointer).toBitmap()

    init {
        matrixDown.postRotate(180F)
        matrixLeft.postRotate(-90F)
        matrixRight.postRotate(90F)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawBitmap(
            bitmap,
            null,
            rectUp,
            null
        )
        canvas.drawBitmap(
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrixDown, true),
            null,
            rectDown,
            null
        )
        canvas.drawBitmap(
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrixLeft, true),
            null,
            rectLeft,
            null
        )
        canvas.drawBitmap(
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrixRight, true),
            null,
            rectRight,
            null
        )
    }
}