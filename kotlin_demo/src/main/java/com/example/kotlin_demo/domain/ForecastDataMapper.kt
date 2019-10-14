package com.example.kotlin_demo.domain

import com.example.kotlin_demo.ForecastResult
import java.text.DateFormat
import java.util.*
import com.example.kotlin_demo.domain.Domain.Forecast as ModelForecast

/**
 * @time 2019/10/14 17:41
 * @desc
 */
public class ForecastDataMapper {

    fun convertFromDataModel(forecast: ForecastResult): Domain.ForecastList {
        return Domain.ForecastList(forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))

    }

    private fun convertForecastListToDomain(list: List<ForecastResult.Forecast>): List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: ForecastResult.Forecast): ModelForecast {
        return ModelForecast(convertDate(forecast.dt), forecast.weather[0].description, forecast.temp.max.toInt(), forecast.temp.min.toInt())
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }
}