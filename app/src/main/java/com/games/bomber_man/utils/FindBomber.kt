package com.games.bomber_man.utils

class FindBomber : (Array<IntArray>) -> Array<Int> {

    override fun invoke(field: Array<IntArray>): Array<Int> {
        for(i in 1..12){
            for(j in 1..30){
                if(field[i][j] == 9){
                    return arrayOf(i, j)
                }
            }
        }
        return arrayOf(0, 0)
    }
}