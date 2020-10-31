package com.games.bomber_man

import android.app.Application
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Build
import android.util.Log
import android.view.SurfaceHolder
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.games.bomber_man.drawer_elements.*
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
) : GameView(contextApp){

    private var speedBomber = 10
    private var wallsVelocity = 0

    private val widthField = 31 * (widthScreen / 16)
    private val heightFieldScore = heightScreen / 10

    private val widthGameTitle = widthScreen / 16
    private val heightGameTitle = (heightScreen - heightFieldScore) / 13

    private val mainThread = MainThread(this)
    private lateinit var bomber: Bomber
    private lateinit var wall: Wall
    private lateinit var breakableWall: BreakableWall

    private var field = SetFieldWalls().setField()
    private var bomberCoordinates = arrayOf(heightGameTitle * 2 + heightFieldScore, widthGameTitle * 2)

    private var arrayDraws: ArrayList<DrawOnCanvas> = ArrayList()

    private var actionButton = Move.NO_MOVE
    private var isTouched = false

    private val gameInputObserver: GameInputObserver = object : GameInputObserver{

        override fun upAction() {
            bomber.setAction(actionButton)

            val i = (bomberCoordinates[0] - speedBomber - heightFieldScore) / heightGameTitle - 1
            val j = (bomberCoordinates[1] - widthGameTitle / 2 + (-1) * wallsVelocity) / widthGameTitle

            if(field[i][j] in 1..2){
                val upperBorder = heightFieldScore + heightGameTitle * (i + 1)
                if(upperBorder <= bomberCoordinates[0] - speedBomber - heightGameTitle
                    && bomberCoordinates[0] - heightGameTitle - speedBomber - upperBorder >= speedBomber){
                    bomberCoordinates[0] = bomberCoordinates[0] - speedBomber
                }else if(upperBorder <= bomberCoordinates[0] - speedBomber - heightGameTitle
                    && bomberCoordinates[0] - heightGameTitle - speedBomber - upperBorder > 0){
                    val difference = bomberCoordinates[0] - heightGameTitle - speedBomber - upperBorder
                    bomberCoordinates[0] = bomberCoordinates[0] - difference
                }
            }else{
                bomberCoordinates[0] = bomberCoordinates[0] - speedBomber
            }

            bomber.setBomberRect(Rect(
                bomberCoordinates[1] - widthGameTitle,
                bomberCoordinates[0] - heightGameTitle,
                bomberCoordinates[1],
                bomberCoordinates[0]
            ))
        }

        override fun downAction() {
            bomber.setAction(actionButton)

            val i = (bomberCoordinates[0] + speedBomber - heightFieldScore) / heightGameTitle
            val j = (bomberCoordinates[1] - widthGameTitle / 2 + (-1) * wallsVelocity) / widthGameTitle

            if(field[i][j] in 1..2){
                val downBorder = heightFieldScore + heightGameTitle * i
                if(downBorder >= bomberCoordinates[0] + speedBomber - heightGameTitle
                    && downBorder - bomberCoordinates[0] - heightGameTitle + speedBomber >= speedBomber){
                    bomberCoordinates[0] = bomberCoordinates[0] + speedBomber
                }else if(downBorder >= bomberCoordinates[0] + speedBomber - heightGameTitle
                    && downBorder - bomberCoordinates[0] - heightGameTitle + speedBomber > 0){
                    val difference = downBorder - bomberCoordinates[0] - heightGameTitle + speedBomber
                    bomberCoordinates[0] = bomberCoordinates[0] + difference
                }
            }else{
                bomberCoordinates[0] = bomberCoordinates[0] + speedBomber
            }

            bomber.setBomberRect(Rect(
                bomberCoordinates[1] - widthGameTitle,
                bomberCoordinates[0] - heightGameTitle,
                bomberCoordinates[1],
                bomberCoordinates[0]
            ))
        }

        override fun rightAction() {
            var move = 0
            bomber.setAction(actionButton)

            val i = (bomberCoordinates[0] - heightFieldScore - heightGameTitle / 2) / heightGameTitle
            val j = (bomberCoordinates[1] + (-1) * wallsVelocity) / widthGameTitle

            if(field[i][j] in 1..2){
                val rightBorder = widthGameTitle * j
                if(rightBorder >= bomberCoordinates[1] + speedBomber - wallsVelocity
                    && rightBorder - bomberCoordinates[1] + speedBomber - wallsVelocity >= speedBomber){
                    move = speedBomber
                }else if(rightBorder >= bomberCoordinates[1] + speedBomber - wallsVelocity
                    && rightBorder - bomberCoordinates[1] + speedBomber - wallsVelocity > 0){
                    move = rightBorder - bomberCoordinates[1] + speedBomber - wallsVelocity
                }
            }else{
                move = speedBomber
            }

            if(bomberCoordinates[1] >= widthGameTitle * 12 && bomberCoordinates[1] + (-1) * wallsVelocity <= widthGameTitle * 27){
                wallsVelocity -= move
                wall.setNewWallsRect(wallsVelocity)
                breakableWall.setNewWallsRect(wallsVelocity)
            }else{
                bomberCoordinates[1] = bomberCoordinates[1] + move
                bomber.setBomberRect(Rect(
                    bomberCoordinates[1] - widthGameTitle,
                    bomberCoordinates[0] - heightGameTitle,
                    bomberCoordinates[1],
                    bomberCoordinates[0]
                ))
            }
        }

        override fun leftAction() {
            bomber.setAction(actionButton)

            var move = 0

            val i = (bomberCoordinates[0] - heightFieldScore - heightGameTitle / 2) / heightGameTitle
            val j = (bomberCoordinates[1] + (-1) * wallsVelocity) / widthGameTitle - 1

            if(field[i][j] in 1..2){
                val rightBorder = widthGameTitle * j
                if(rightBorder >= bomberCoordinates[1] - speedBomber - wallsVelocity
                    && bomberCoordinates[1] - speedBomber - wallsVelocity - rightBorder >= speedBomber){
                    move = speedBomber
                }else if(rightBorder >= bomberCoordinates[1] - speedBomber - wallsVelocity
                    && bomberCoordinates[1] - speedBomber - wallsVelocity - rightBorder > 0){
                    move = bomberCoordinates[1] - speedBomber - wallsVelocity - rightBorder
                }
            }else{
                move = speedBomber
            }

            if(bomberCoordinates[1] <= widthGameTitle * 5 && bomberCoordinates[1] - wallsVelocity >= widthGameTitle * 5){
                wallsVelocity += move
                wall.setNewWallsRect(wallsVelocity)
                breakableWall.setNewWallsRect(wallsVelocity)
            }else{
                bomberCoordinates[1] = bomberCoordinates[1] - move
                bomber.setBomberRect(Rect(
                    bomberCoordinates[1] - widthGameTitle,
                    bomberCoordinates[0] - heightGameTitle,
                    bomberCoordinates[1],
                    bomberCoordinates[0]
                ))
            }
        }

        override fun setBomb() {

        }
    }

    private val inputObserver: InputObserver = object : InputObserver{
        override fun isButtonTouched(isTouched: Boolean, action: Move) {
            if(isTouched){
                actionButton = action
            }
            this@GameDrawer.isTouched = isTouched
        }
    }

    private val applicationClass = application as com.games.bomber_man.Application
    private val onTouchEventGame = OnTouchEventGame(heightScreen, widthScreen, inputObserver)

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

        wall = Wall(
            widthField  = widthField,
            heightField = heightFieldScore,
            wallBitmap = applicationClass.getWall(),
            field = field,
            heightWall = heightGameTitle,
            widthWall = widthGameTitle,
            resources = contextApp.resources
        )

        breakableWall = BreakableWall(
            widthField  = widthField,
            heightField = heightFieldScore,
            wallBitmap = applicationClass.getBreackableWall(),
            animationBitmap = applicationClass.getBreakableAnimation(),
            field = field,
            heightWall = heightGameTitle,
            widthWall = widthGameTitle,
            resources = contextApp.resources,
            level = level
        )

        arrayDraws.add(Score(widthScreen, heightFieldScore, lives, contextApp))
        arrayDraws.add(wall)
        arrayDraws.add(breakableWall)
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
                Move.RIGHT -> gameInputObserver.rightAction()
                Move.LEFT -> gameInputObserver.leftAction()
                Move.UP -> gameInputObserver.upAction()
                Move.DOWN -> gameInputObserver.downAction()
                Move.BOMB -> gameInputObserver.setBomb()
            }
        }else{
            bomber.isBomberMoving(false)
        }
    }
}