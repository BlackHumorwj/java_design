package com.example.kotlin_demo.domain

/**
 * @time 2019/10/14 17:34
 * @desc
 */
class Domain {

    data class ForecastList(val city: String, val country: String, val dailyForecast: List<Forecast>) {

        operator fun get(position: Int): Forecast = dailyForecast[position]

        fun size(): Int = dailyForecast.size


    }

    data class Forecast(val date: String, val description: String, val high: Int, val low: Int, val iconUrl: String)


}