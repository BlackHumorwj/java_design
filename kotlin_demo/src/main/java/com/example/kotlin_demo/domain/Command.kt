package com.example.kotlin_demo.domain

/**
 * @time 2019/10/14 17:32
 * @desc
 */
 interface Command<T> {

    fun execute(): T

}