package com.example.kotlin_demo.demo

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @time 2019/10/17 11:07
 * @desc
 */
abstract class Delegate<T> : ReadWriteProperty<Any?, T> {

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    }

}