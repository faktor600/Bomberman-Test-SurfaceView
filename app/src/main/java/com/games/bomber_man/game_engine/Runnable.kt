package com.games.bomber_man.game_engine

import com.games.bomber_man.BuildConfig
import com.games.bomber_man.utils.RunningCheck
import java.lang.Runnable

class Runnable(private val gameView: GameView) : Runnable, RunningCheck {

    private var running: Boolean = false

    private val holder = gameView.holder

    override fun run() {
        val tickPS: Long = 1000 / BuildConfig.FPS

        while (running){
            val startTime = System.currentTimeMillis()
            if(!Thread.interrupted()){
                val canvas = holder.lockCanvas()
                synchronized(holder){
                    gameView.draw(canvas)
                }
                holder.unlockCanvasAndPost(canvas)

                when(val sleepTime = tickPS - System.currentTimeMillis() - startTime){
                    0L -> Thread.sleep(10)
                    else -> Thread.sleep(sleepTime)
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