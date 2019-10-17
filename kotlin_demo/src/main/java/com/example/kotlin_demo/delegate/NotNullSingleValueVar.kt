package com.example.kotlin_demo.delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @time 2019/10/17 14:38
 * @desc
 */
class NotNullSingleValueVar<T> : ReadWriteProperty<Any?, T> {

    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (value == null) {
            throw IllegalStateException("${property.name} not initialized")
        } else {
            return value as T
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {

        if (this.value == null) {
            this.value = value
        } else {
            throw IllegalStateException("${property.name} already initialized")
        }

    }

}