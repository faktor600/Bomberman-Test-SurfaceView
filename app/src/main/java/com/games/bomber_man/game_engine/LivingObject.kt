package com.games.bomber_man.game_engine

import android.graphics.Canvas
import com.games.bomber_man.utils.DrawOnCanvas

abstract class LivingObject : DrawOnCanvas {

    abstract var isAlive: Boolean

    abstract fun moveObject(canvas: Canvas)

    override fun draw(canvas: Canvas) {
        moveObject(canvas)
    }

    fun isObjectAlive(): Boolean{
        return isAlive
    }
}