package com.example.kotlin_demo.demo

import android.content.Context
import android.widget.Toast

/**
 * @time 2019/9/23 12:00
 * @desc  abstract
 */
open class Person(name: String) {


    var name: String = ""
        get() = field.toUpperCase()
        set(value) {
            field = "Name =  $name"
        }

    var age: Int = 0
        get() = age + 1
        set(value) {
            field = value + 1
        }


    /**
     * 空安全
     */
    private fun nullSafe() {

        //默认不可以为null
        //var notNullArtist: Artist = null;

        //可以为null 用？指定
        var artist: Artist? = null



        artist?.toString()

        //是null的情况下替代值
        var name = artist?.name ?: "empty"


        print(name);

    }



    fun Context.toast(){
        Toast.makeText(this,"ddd", Toast.LENGTH_LONG).show()
    }

}