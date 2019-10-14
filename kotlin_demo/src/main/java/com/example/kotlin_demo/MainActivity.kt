package com.example.kotlin_demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.kotlin_demo.domain.RequestcastCommand
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var forecastList = findViewById<RecyclerView>(R.id.forecast_list)

        forecastList.layoutManager = LinearLayoutManager(this)



        async() {
            val result = RequestcastCommand("94043").execute()

            uiThread {
                forecastList.adapter = ForecastListAdapter(result)

            }

        }

    }


}
