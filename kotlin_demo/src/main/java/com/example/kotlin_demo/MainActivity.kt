package com.example.kotlin_demo

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_demo.domain.RequestcastCommand
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var forecastList = findViewById<RecyclerView>(R.id.forecast_list)

        forecastList.layoutManager = LinearLayoutManager(this)



        async {

            //1、异步发起请求
            val result = RequestcastCommand("94043").execute()

            uiThread {
                forecastList.adapter = ForecastListAdapter(result) { forecast ->
                    toast(forecast.date)
                }

            }

        }


        // demoFun()

        supportsLollipop {

            Log.e("xx内联函数", Build.VERSION_CODES.LOLLIPOP.toString())

        }


    }


    //内联函数
    inline fun supportsLollipop(code:()->Unit){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            code()
        }
    }


    private fun demoFun() {

        val person = Person("lily")


        val data1 = ForecastResult.Coordinates(1f, 5f)
        val data2 = data1.copy(25f, 23.3f)

        //多声明
        val (lon, lat) = data1

    }


    operator fun ViewGroup.get(position: Int): View = getChildAt(position)

    fun View.cxt(): Context = context

}
