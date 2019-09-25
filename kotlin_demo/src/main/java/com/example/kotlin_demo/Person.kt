package com.example.kotlin_demo

/**
 * @time 2019/9/23 12:00
 * @desc
 */
class Person(name: String) {


    var maxInt: Int = Int.MIN_VALUE

    private val name = "10"

    var name1 = "11"

    fun main(args: Array<String>): Unit {

        println("hello kotlin")

        var n = name.toInt()


        println(name == name1)


        println(name === name1)


        val first = name.first()

        name.firstOrNull { it == 'g' }

        name.last { it == 'i' }


        println(findBook("1001"))

        var map  = HashMap<String,Int>()

        map["1"] = 1

    }

    private fun findBook(bookId: String): String {
        return bookId

    }


}