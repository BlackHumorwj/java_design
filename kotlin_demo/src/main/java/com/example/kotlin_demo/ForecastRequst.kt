package com.example.kotlin_demo

import android.util.Log
import com.google.gson.Gson
import java.net.URL

/**
 * @time 2019/10/14 17:04
 * @desc
 */
class ForecastRequst (val zipCode:String){

    companion object{

        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"

        private val URLS  ="http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"

        private val COMPLETE_URL = "$URLS&APPID=$APP_ID&q="

    }



    fun execute():ForecastResult{

        val forecastJsonStr = URL(COMPLETE_URL+zipCode).readText()

        Log.i("xxx",forecastJsonStr)

        return Gson().fromJson(forecastJsonStr,ForecastResult::class.java)

    }


}