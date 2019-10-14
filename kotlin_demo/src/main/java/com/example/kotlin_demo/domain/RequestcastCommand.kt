package com.example.kotlin_demo.domain

import com.example.kotlin_demo.ForecastRequst

/**
 * @time 2019/10/14 18:06
 * @desc
 */
public class RequestcastCommand (val zipCode:String):Command<Domain.ForecastList>{
    override fun execute(): Domain.ForecastList {


        val  forecastRequest  =  ForecastRequst(zipCode)


        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())


    }


}