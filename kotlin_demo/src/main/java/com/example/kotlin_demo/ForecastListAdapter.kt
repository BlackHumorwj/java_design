package com.example.kotlin_demo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_demo.domain.Domain
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*
/**
 * @time 2019/10/11 18:09
 * @desc
 */
class ForecastListAdapter(private val weekForecast: Domain.ForecastList,private val listener: (Domain.Forecast) -> Unit) : RecyclerView.Adapter<ForecastListAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {


        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)


        return Holder(view)

    }

    override fun getItemCount(): Int {
        return weekForecast.size()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindView(weekForecast[position], listener)
    }


    class Holder(private val view: View) : RecyclerView.ViewHolder(view) {


        fun bindView(forecastItem: Domain.Forecast, listener: (Domain.Forecast) -> Unit) {

            with(forecastItem) {

                Picasso.with(view.context).load(iconUrl).into(view.icon)

                view.date.text = date
                view.description.text = description
                view.maxTemperature.text = high.toString()
                view.minTemperature.text = low.toString()

                view.setOnClickListener {
                    listener(this)
                }

            }


        }


    }


}