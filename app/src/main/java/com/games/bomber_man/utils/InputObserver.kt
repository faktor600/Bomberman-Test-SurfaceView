package com.games.bomber_man.utils

interface InputObserver {

    fun isButtonTouched(isTouched: Boolean, action: Move)
}