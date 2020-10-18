package com.games.bomber_man

import android.app.Application
import android.content.Context
import android.view.SurfaceHolder
import com.games.bomber_man.game_engine.GameView
import com.games.bomber_man.game_engine.MainThread
import com.games.bomber_man.game_engine.Runnable
import com.games.bomber_man.utils.HolderCallback

class GameDrawer(
    context: Context,
    private val application: Application,
    private val widthScreen: Int,
    private val heightScreen: Int,
    private val level: Int
) : GameView(context) {

    private val mainThread = MainThread(Runnable(this))

    private var field = Array(13) { IntArray(31) }

    init {
        val widthField = 31 * (widthScreen / 16)
        val widthTitle = widthScreen / 16

        val heightField = heightScreen -  heightScreen / 4
        val heightTitle = heightField / 13
        
    }

    override fun getSurfaceHolderCallback(): SurfaceHolder.Callback {
        return HolderCallback(mainThread)
    }

    override fun initDrawLogic() {

    }
}