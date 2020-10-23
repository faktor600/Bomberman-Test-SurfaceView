package com.games.bomber_man.utils

interface GameInputObserver {

    fun upAction()

    fun downAction()

    fun rightAction()

    fun leftAction()

    fun setBomb()

    fun isButtonTouched(isTouched: Boolean, action: String)
}