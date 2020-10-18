package com.games.bomber_man.utils

class SetFieldWalls {

    fun setField() : Array<IntArray>{
        val field = Array(13) { IntArray(31) }
        for (i in 1..13) {
            for (j in 1..31) {
                if (i == 1 || i == 13 || j == 1 || j == 31 || i % 2 != 0 && j % 2 != 0) {
                    field[i - 1][j - 1] = 1
                }
            }
        }
        return field
    }
}