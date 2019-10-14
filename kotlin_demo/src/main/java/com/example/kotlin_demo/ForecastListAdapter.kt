package com.example.kotlin_demo

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.example.kotlin_demo.domain.Domain

/**
 * @time 2019/10/11 18:09
 * @desc
 */
class ForecastListAdapter(val weekForecast: Domain.ForecastList) : RecyclerView.Adapter<ForecastListAdapter.Holder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  Holder {
        return Holder(TextView(parent.context))

    }

    override fun getItemCount(): Int {
        return weekForecast.dailyForecast.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(weekForecast.dailyForecast[position]) {

            holder.textView.text = "$date - $description - $high/$low"

        }
    }


    class Holder(val textView: TextView) : RecyclerView.ViewHolder(textView)


}