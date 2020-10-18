package com.games.bomber_man

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.os.Build
import android.view.SurfaceHolder
import androidx.annotation.RequiresApi
import com.games.bomber_man.drawer_elements.Score
import com.games.bomber_man.game_engine.GameView
import com.games.bomber_man.game_engine.MainThread
import com.games.bomber_man.game_engine.Runnable
import com.games.bomber_man.utils.HolderCallback

class GameDrawer(
    private val contextApp: Context,
    private val application: Application,
    private val widthScreen: Int,
    private val heightScreen: Int,
    private val level: Int
) : GameView(contextApp) {

    private val mainThread = MainThread(this)

    private var field = Array(13) { IntArray(31) }

    private lateinit var score: Score

    init {
        val widthField = 31 * (widthScreen / 16)
        val widthTitle = widthScreen / 16

        val heightField = heightScreen -  heightScreen / 4
        val heightTitle = heightField / 13

        score = Score(widthScreen, heightScreen, level, contextApp)
    }

    override fun getSurfaceHolderCallback(): SurfaceHolder.Callback {
        return HolderCallback(mainThread)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initDrawLogic(canvas: Canvas) {
        score.draw(canvas)
    }
}