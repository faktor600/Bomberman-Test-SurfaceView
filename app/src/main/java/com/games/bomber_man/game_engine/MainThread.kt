package com.games.bomber_man.game_engine

import com.games.bomber_man.BuildConfig
import com.games.bomber_man.utils.RunningCheck

class MainThread(private val gameView: GameView) : Thread(), RunningCheck{

    private var running: Boolean = false

    private val holder = gameView.holder

    override fun run() {
        val tickPS: Long = 1000 / BuildConfig.FPS

        while (running){
            val startTime = System.currentTimeMillis()
            if(!interrupted()){
                val canvas = holder.lockCanvas()
                synchronized(holder){
                    gameView.draw(canvas)
                }
                holder.unlockCanvasAndPost(canvas)

                val sleepTime = tickPS - System.currentTimeMillis() - startTime
                if (sleepTime > 0L){
                    sleep(sleepTime)
                }else{
                    sleep(10)
                }
            }else{
                throw InterruptedException()
            }
        }
    }

    override fun setRunning(run: Boolean){
        running = run
    }
}