package com.example.article_demo

/**
 * @time 2019/11/14 9:45
 * @desc
 */
class FreshToken {

    private var state: Int = 0

   var Lock  = Any()


    @Synchronized  fun setSate(state: Int) {
        this.state = state

    }


   @Synchronized fun  getState(): Int {

        return state
    }




    fun freshToken(){







    }




}