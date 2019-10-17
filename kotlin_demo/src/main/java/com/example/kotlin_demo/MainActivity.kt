package com.example.kotlin_demo

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_demo.delegate.MapDeletegate
import com.example.kotlin_demo.delegate.MyDeletegate
import com.example.kotlin_demo.demo.Person
import com.example.kotlin_demo.domain.RequestcastCommand
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


        lambdaFun()

        // demoFun()

        supportsLollipop {

            Log.e("xx内联函数", Build.VERSION_CODES.LOLLIPOP.toString())

        }


        delegateFun()


    }

    private fun delegateFun() {

        var name: String by MyDeletegate()

        name = "小李"


        println(name)


        var config = MapDeletegate(mapOf(
                "width" to 1000,
                "height" to 200,
                "dp" to 500,
                "deviceName" to "hw"
        ))



    }

    private fun lambdaFun() {
        textTextView.setOnClickListener {
            toast(it.isEnabled.toString())

        }

        textTextView.setOnClickListener { view -> toast(view.isEnabled.toString()) }



        textTextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {


            }
        })

    }


    private fun constructorFun() {
        // var  c = C(1)
    }


    fun setListener(listener: (View) -> Unit) {


    }


    //内联函数
    inline fun supportsLollipop(code: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
