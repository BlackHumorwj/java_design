package com.example.kotlin_demo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlin_demo.domain.Domain
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

/**
 * @time 2019/10/11 18:09
 * @desc
 */
class ForecastListAdapter(val weekForecast: Domain.ForecastList, val listener: (Domain.Forecast) -> Unit) : RecyclerView.Adapter<ForecastListAdapter.Holder>() {


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


    class Holder(val view: View) : RecyclerView.ViewHolder(view) {

        private val iconView: ImageView
        private val dateView: TextView
        private val descriptionView: TextView
        private val maxTemperatureView: TextView
        private val minTemperatureView: TextView

        init {

            iconView = view.find(R.id.icon)
            dateView = view.find(R.id.date)
            descriptionView = view.find(R.id.description)
            maxTemperatureView = view.find(R.id.maxTemperature)
            minTemperatureView = view.find(R.id.minTemperature)

        }

        fun bindView(forecastItem: Domain.Forecast, listener: (Domain.Forecast) -> Unit) {

            with(forecastItem) {

                Picasso.with(view.context).load(iconUrl).into(iconView)

                dateView.text = date
                descriptionView.text = description
                maxTemperatureView.text = high.toString()
                minTemperatureView.text = low.toString()

                view.setOnClickListener {
                    listener(this)
                }

            }


        }


    }


}