package com.games.bomber_man

import android.app.Application
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap

class Application : Application() {

    private lateinit var wall: Bitmap
    private lateinit var breakableWall: Bitmap
    private lateinit var door: Bitmap
    private lateinit var explosion: Bitmap

    private lateinit var bomber: Bitmap
    private lateinit var bomberDeath: Bitmap
    private lateinit var bomb: Bitmap

    private lateinit var enemies: Bitmap

    private lateinit var bonuses: Bitmap

    private lateinit var score: Bitmap

    override fun onCreate() {
        super.onCreate()
        val resource = resources.getDrawable(R.drawable.sprites).toBitmap()
        val widthTitle = resource.width/ 14
        val heightTitle = resource.height / 23

        bomber = Bitmap.createBitmap(resource, 0, 0, widthTitle * 6, heightTitle * 2)
        bomberDeath = Bitmap.createBitmap(resource, 0, heightTitle * 2, widthTitle * 7, heightTitle)
        bomb = Bitmap.createBitmap(resource, 0, heightTitle * 3, widthTitle * 3, heightTitle)

        wall = Bitmap.createBitmap(resource, widthTitle * 3, heightTitle * 3, widthTitle, heightTitle)
        breakableWall = Bitmap.createBitmap(resource, widthTitle * 4, heightTitle * 3, widthTitle * 7, heightTitle)

        door = Bitmap.createBitmap(resource, widthTitle * 11, heightTitle * 3, widthTitle, heightTitle)

        explosion = Bitmap.createBitmap(resource, 0, heightTitle * 4, widthTitle * 10, heightTitle * 10)

        bonuses = Bitmap.createBitmap(resource, 0, heightTitle * 14, widthTitle * 12, heightTitle)

        enemies = Bitmap.createBitmap(resource, 0, heightTitle * 15, widthTitle * 11, heightTitle * 8)

        score = Bitmap.createBitmap(resource, widthTitle * 7, heightTitle * 21, widthTitle * 3, heightTitle * 2)
    }

    fun getScore(): Bitmap{
        return score
    }

    fun getBomberDeathAnimation(): Bitmap{
        return bomberDeath
    }

    fun getBonuses(): Bitmap{
        return bonuses
    }

    fun getEnemies(): Bitmap{
        return enemies
    }

    fun getBomb(): Bitmap{
        return bomb
    }

    fun getDoor(): Bitmap{
        return door
    }

    fun getExplotaion(): Bitmap{
        return explosion
    }

    fun getWall(): Bitmap{
        return wall
    }

    fun getBomber(): Bitmap{
        return bomber
    }

    fun getBreackableWall(): Bitmap{
        return breakableWall
    }
}