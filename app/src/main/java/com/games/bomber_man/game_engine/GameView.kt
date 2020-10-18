package com.games.bomber_man.game_engine

import android.content.Context
import android.graphics.Canvas
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.games.bomber_man.utils.HolderCallback

abstract class GameView(context: Context): SurfaceView(context) {

    abstract fun getSurfaceHolderCallback(): SurfaceHolder.Callback

    abstract fun initDrawLogic(canvas: Canvas)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        holder.addCallback(getSurfaceHolderCallback())
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        initDrawLogic(canvas)
    }
}