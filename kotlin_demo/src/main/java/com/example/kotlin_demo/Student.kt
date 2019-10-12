package com.example.kotlin_demo

import android.content.Context
import android.widget.Toast

/**
 * @time 2019/10/11 17:00
 * @desc
 */
class Student(sex: String, age: Long) : Person(sex) {




    init {
        toast(null, "toast")
        getString()
    }


    private fun add(num1: Long, num2: Long): Long {
        return num1 + num2
    }


    private fun toast(context: Context?, message: String, length: Int = Toast.LENGTH_LONG): Unit {
        Toast.makeText(context, message, length).show()
    }


    private fun getString(message: String = "默认鬼") {

        println("$message" + "这是什么鬼哟")
    }


}