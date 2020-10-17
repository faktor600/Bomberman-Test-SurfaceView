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
        setContentView(GameDrawer(this))
    }

    fun onClickContinue(view: View) {
        setContentView(GameDrawer(this))
    }
}
