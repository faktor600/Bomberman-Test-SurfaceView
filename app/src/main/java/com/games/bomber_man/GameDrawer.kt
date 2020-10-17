package com.games.bomber_man

import android.content.Context
import android.view.SurfaceHolder
import com.games.bomber_man.game_engine.GameView
import com.games.bomber_man.game_engine.MainThread
import com.games.bomber_man.game_engine.Runnable
import com.games.bomber_man.utils.HolderCallback

class GameDrawer(context: Context) : GameView(context) {

    override fun getSurfaceHolderCallback(): SurfaceHolder.Callback {
        return HolderCallback(MainThread(Runnable(this)))
    }

    override fun initDrawLogic() {

    }
}