package com.games.bomber_man

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Build
import android.view.GestureDetector
import android.view.SurfaceHolder
import androidx.annotation.RequiresApi
import com.games.bomber_man.drawer_elements.Arrows
import com.games.bomber_man.drawer_elements.Bomber
import com.games.bomber_man.drawer_elements.Score
import com.games.bomber_man.drawer_elements.Wall
import com.games.bomber_man.game_engine.GameView
import com.games.bomber_man.game_engine.MainThread
import com.games.bomber_man.utils.*

class GameDrawer(
    private val contextApp: Context,
    private val application: Application,
    private val widthScreen: Int,
    private val heightScreen: Int,
    private val level: Int,
    private val lives: Int
) : GameView(contextApp) {

    private var speedBomber = 5

    private val widthField = 31 * (widthScreen / 16)
    private val heightFieldScore = heightScreen / 10
    private val heightGameField = heightScreen - heightFieldScore

    private val widthGameTitle = widthScreen / 16
    private val heightGameTitle = (heightScreen - heightFieldScore) / 13

    private val mainThread = MainThread(this)
    private lateinit var bomber: Bomber

    private var field = SetFieldWalls().setField()
    private var bomberCoordinates = arrayOf(heightGameTitle * 2 + heightFieldScore, widthGameTitle * 2)

    private var arrayDraws: ArrayList<DrawOnCanvas> = ArrayList()

    private var actionButton = ""
    private var isTouched = false

    private val gameInputObserver: GameInputObserver = object : GameInputObserver{

        private val checkCollision = CheckCollision()

        override fun upAction() {
            bomberCoordinates[0] = bomberCoordinates[0] - speedBomber
            bomber.setAction(actionButton)
            bomber.setBomberRect(Rect(
                bomberCoordinates[1] - widthGameTitle,
                bomberCoordinates[0] - heightGameTitle,
                bomberCoordinates[1],
                bomberCoordinates[0]
            ))
        }

        override fun downAction() {
            bomberCoordinates[0] = bomberCoordinates[0] + speedBomber
            bomber.setAction(actionButton)
            bomber.setBomberRect(Rect(
                bomberCoordinates[1] - widthGameTitle,
                bomberCoordinates[0] - heightGameTitle,
                bomberCoordinates[1],
                bomberCoordinates[0]
            ))
        }

        override fun rightAction() {
            bomberCoordinates[1] = bomberCoordinates[1] + speedBomber
            bomber.setAction(actionButton)
            bomber.setBomberRect(Rect(
                bomberCoordinates[1] - widthGameTitle,
                bomberCoordinates[0] - heightGameTitle,
                bomberCoordinates[1],
                bomberCoordinates[0]
            ))
        }

        override fun leftAction() {
            bomberCoordinates[1] = bomberCoordinates[1] - speedBomber
            bomber.setAction(actionButton)
            bomber.setBomberRect(Rect(
                bomberCoordinates[1] - widthGameTitle,
                bomberCoordinates[0] - heightGameTitle,
                bomberCoordinates[1],
                bomberCoordinates[0]
            ))
        }

        override fun setBomb() {

        }

        override fun isButtonTouched(isTouched: Boolean, action: String) {
            if(isTouched){
                actionButton = action
            }
            this@GameDrawer.isTouched = isTouched
        }
    }

    private val applicationClass = application as com.games.bomber_man.Application
    private val onTouchEventGame = OnTouchEventGame(heightScreen, widthScreen, gameInputObserver)

    init {

        this.setOnTouchListener(onTouchEventGame)

        bomber = Bomber(
            widthField = widthField,
            heightField = heightFieldScore,
            widthTitle = widthGameTitle,
            heightTitle = heightGameTitle,
            field = field,
            bitmapBomber = applicationClass.getBomber(),
            bitmapDeath = applicationClass.getBomberDeathAnimation(),
            resources = contextApp.resources
        )

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
        arrayDraws.add(bomber)
        arrayDraws.add(Arrows(widthScreen, heightScreen, resources))
    }

    override fun getSurfaceHolderCallback(): SurfaceHolder.Callback {
        return HolderCallback(mainThread)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initDrawLogic(canvas: Canvas) {
        for(drawClass in arrayDraws){
            drawClass.draw(canvas)
        }
        if(isTouched){
            bomber.isBomberMoving(true)
            when(actionButton){
                "RIGHT" -> gameInputObserver.rightAction()
                "LEFT" -> gameInputObserver.leftAction()
                "UP" -> gameInputObserver.upAction()
                "DOWN" -> gameInputObserver.downAction()
                "BOMB" -> gameInputObserver.setBomb()
            }
        }else{
            bomber.isBomberMoving(false)
        }
    }
}