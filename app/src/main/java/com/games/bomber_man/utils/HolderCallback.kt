package com.games.bomber_man.utils

import android.view.SurfaceHolder
import com.games.bomber_man.game_engine.MainThread

class HolderCallback(private val gameThread: MainThread) : SurfaceHolder.Callback {

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {}

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
        var retry = true
        gameThread.setRunning(false)
        while (retry) {
            try {
                gameThread.join()
                retry = false
            } catch (e: InterruptedException){
                e.printStackTrace()
            }
        }
    }

    override fun surfaceCreated(p0: SurfaceHolder?) {
        gameThread.setRunning(true)
        gameThread.start()
    }
}