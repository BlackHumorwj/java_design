package com.example.kotlin_demo.domain

/**
 * @time 2019/10/14 17:34
 * @desc
 */
class Domain {

    data class ForecastList(val city: String, val country: String, val dailyForecast: List<Forecast>)

    data class Forecast(val date: String, val description: String, val high: Int, val low: Int)


}