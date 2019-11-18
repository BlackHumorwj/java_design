package com.example.kotlin_demo

import android.app.Application
import com.example.kotlin_demo.objects.DelegatesExt

/**
 * @time 2019/10/17 10:43
 * @desc
 */
class App : Application() {

    //object 关键字可以表达两种含义：一种是对象表达式,另一种是 对象声明。

    //用object 修饰的类为静态类，里面的方法和变量都为静态的。

    //companion object 修饰为伴生对象,伴生对象在类中只能存在一个
    companion object {

         //        private var instance: Application? = null

         //        fun instantce() = instance!!

         //var instance: App by Delegates.notNull()

         //3
        var instance: App by DelegatesExt.notNullSingleValue()



    }


    override fun onCreate() {
        super.onCreate()
        instance = this

    }


}