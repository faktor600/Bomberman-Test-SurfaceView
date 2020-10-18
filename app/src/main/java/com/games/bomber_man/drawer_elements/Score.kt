package com.games.bomber_man.drawer_elements

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.games.bomber_man.R

class Score(
    private val width: Int,
    private val height: Int,
    private val level: Int,
    private val context: Context
) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun draw(canvas: Canvas){
        var paint = Paint()
        paint.color = ResourcesCompat.getColor(context.resources, R.color.grey, null)
        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), paint)

        paint = Paint()
        paint.textSize = 26F
        paint.color = ResourcesCompat.getColor(context.resources, R.color.white, null)
        paint.fontFeatureSettings = ResourcesCompat.getFont(context, R.font.game_font).toString()
        canvas.drawText("TIME", (width/10).toFloat(), (height/10).toFloat(), paint)
    }
}