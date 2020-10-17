package com.games.bomber_man.game_engine

import com.games.bomber_man.utils.RunningCheck

class MainThread(private val runnable: Runnable) : Thread(runnable), RunningCheck{

    override fun setRunning(run: Boolean){
        runnable.setRunning(run)
    }
}