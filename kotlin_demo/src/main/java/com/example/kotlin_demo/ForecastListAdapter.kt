package com.example.kotlin_demo

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView

/**
 * @time 2019/10/11 18:09
 * @desc
 */
class ForecastListAdapter(val list: List<String>) : RecyclerView.Adapter<ForecastListAdapter.Holder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  Holder {
        return Holder(TextView(parent.context))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.textView.text = list[position]
    }


    class Holder(val textView: TextView) : RecyclerView.ViewHolder(textView)


}