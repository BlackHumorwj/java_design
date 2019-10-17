package com.example.kotlin_demo.delegate

import kotlin.reflect.KProperty

/**
 * @time 2019/10/17 14:03
 * @desc
 */
class MyDeletegate {

    operator fun setValue(refValue: Any?, property: KProperty<*>, value: String): Unit {

        println("$refValue 的 ${property.name} 属性赋值为 $value")

    }


    operator fun getValue(refValue: Any?, property: KProperty<*>): String {
        return "$refValue,这里委托了${property.name}"

    }


}