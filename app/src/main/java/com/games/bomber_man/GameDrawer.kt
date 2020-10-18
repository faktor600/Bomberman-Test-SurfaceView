package com.games.bomber_man

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.view.SurfaceHolder
import androidx.annotation.RequiresApi
import com.games.bomber_man.drawer_elements.Bomber
import com.games.bomber_man.drawer_elements.Score
import com.games.bomber_man.drawer_elements.Wall
import com.games.bomber_man.game_engine.GameView
import com.games.bomber_man.game_engine.MainThread
import com.games.bomber_man.utils.DrawOnCanvas
import com.games.bomber_man.utils.HolderCallback
import com.games.bomber_man.utils.SetFieldWalls

class GameDrawer(
    private val contextApp: Context,
    private val application: Application,
    private val widthScreen: Int,
    private val heightScreen: Int,
    private val level: Int,
    private val lives: Int
) : GameView(contextApp) {

    private val mainThread = MainThread(this)

    private var field = SetFieldWalls().setField()

    private var arrayDraws: ArrayList<DrawOnCanvas> = ArrayList()

    private val applicationClass = application as com.games.bomber_man.Application

    init {
        val widthField = 31 * (widthScreen / 16)
        val heightFieldScore = heightScreen / 10
        val heightGameField = heightScreen - heightFieldScore

        val widthGameTitle = widthScreen / 16
        val heightGameTitle = (heightScreen - heightFieldScore) / 13

        arrayDraws.add(Score(widthScreen, heightFieldScore, lives, contextApp))
        arrayDraws.add(Wall(
            widthField  = widthField,
            heightField = heightFieldScore,
            wallBitmap = applicationClass.getWall(),
            field = field,
            heightWall = heightGameTitle,
            widthWall = widthGameTitle,
            resources = contextApp.resources
        ))
        arrayDraws.add(Bomber(
            widthField = widthField,
            heightField = heightFieldScore,
            widthTitle = widthGameTitle,
            heightTitle = heightGameTitle,
            field = field,
            bitmapBomber = applicationClass.getBomber(),
            bitmapDeath = applicationClass.getBomberDeathAnimation(),
            resources = contextApp.resources
        ))
    }

    override fun getSurfaceHolderCallback(): SurfaceHolder.Callback {
        return HolderCallback(mainThread)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initDrawLogic(canvas: Canvas) {
        for(drawClass in arrayDraws){
            drawClass.draw(canvas)
        }
    }
}