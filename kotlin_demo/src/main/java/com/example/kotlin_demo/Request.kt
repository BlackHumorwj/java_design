package com.example.kotlin_demo

import android.util.Log
import java.net.URL

/**
 * @time 2019/10/12 15:02
 * @desc
 */
public class Request(val url: String) {


    public fun run() {

        val jsonStr = URL(url).readText()
        Log.e(javaClass.simpleName, "response = $jsonStr")

    }


}