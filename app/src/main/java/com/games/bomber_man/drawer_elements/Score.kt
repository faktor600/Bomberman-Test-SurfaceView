package com.games.bomber_man.drawer_elements

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.games.bomber_man.R
import com.games.bomber_man.utils.DrawOnCanvas

class Score(
    private val width: Int,
    private val height: Int,
    private val livesLeft: Int,
    private val context: Context
) : DrawOnCanvas {

    private var heightItems: Float = (height/1.5).toFloat()

    private val paint = Paint()
    private val paintText = Paint()

    init {
        paint.color = ResourcesCompat.getColor(context.resources, R.color.grey, null)

        paintText.textSize = 50F
        paintText.color = ResourcesCompat.getColor(context.resources, R.color.white, null)
        paintText.typeface  = ResourcesCompat.getFont(context, R.font.game_font)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun draw(canvas: Canvas){
        //Серое поле
        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), paint)
        //Время
        canvas.drawText(
            context.resources.getString(R.string.game_time_string),
            (width/10).toFloat(),
            heightItems,
            paintText
        )
        //Осталось жизней
        canvas.drawText(
            context.resources.getString(R.string.game_lives_left),
            (width / 1.5).toFloat(),
            heightItems,
            paintText
        )
        //Сами жизни
        canvas.drawText(
            livesLeft.toString(),
            (width / 1.31).toFloat(),
            heightItems,
            paintText
        )
    }
}