package com.example.kotlin_demo.objects

import com.example.kotlin_demo.delegate.NotNullSingleValueVar
import kotlin.properties.ReadWriteProperty

/**
 * @time 2019/10/17 14:48
 * @desc
 */
object DelegatesExt {

    //工具类调用
    fun <T> notNullSingleValue(): ReadWriteProperty<Any?, T> = NotNullSingleValueVar<T>()



}