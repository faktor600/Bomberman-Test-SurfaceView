package com.games.bomber_man

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager

class StartActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferences = getPreferences(Context.MODE_PRIVATE)
        val level = preferences.getInt(getString(R.string.level_key), 0)
        val score = preferences.getInt(getString(R.string.score_key), 0)
    }

    fun onClickStart(view: View) {
        val widthScreen = windowManager.defaultDisplay.width
        val heightScreen = windowManager.defaultDisplay.height
        setContentView(GameDrawer(
            context = this,
            application = application,
            widthScreen = widthScreen,
            heightScreen = heightScreen,
            level = 0
        ))
    }

    fun onClickContinue(view: View) {
        val widthScreen = windowManager.defaultDisplay.width
        val heightScreen = windowManager.defaultDisplay.height
        setContentView(GameDrawer(
            context = this,
            application = application,
            widthScreen = widthScreen,
            heightScreen = heightScreen,
            level = 0
        ))
    }
}
