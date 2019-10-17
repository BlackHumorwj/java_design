package com.example.kotlin_demo.collection

/**
 * @time 2019/10/17 16:35
 * @desc
 */
class CollectionDemo {


    //总数操作符
    class Zs {

        private val items = listOf(1, 2, 5, 1, 4, 5)

        /**
         * 如果至少有一个元素符合给出的判断条件，则返回true。
         */
        fun listAny() {

            assert(items.any { it % 2 == 0 })

            assert(items.any { it > 4 })

        }

        /**
         * 如果全部的元素符合给出的判断条件，则返回true。
         */
        fun allList() {

            assert(items.all { it % 3 == 0 })

            assert(items.all { it > 3 })

        }


        /**
         * 返回满足条件的个数
         */
        fun countList() {

            var count = items.count { it > 0 }

            println(count)

        }

        /**
         * 在一个初始值的基础上从第一项到最后一项通过一个函数累计所有的元素。
         */
        fun foldList() {

            items.fold(4) { total, n ->
                total + n
            }

        }


        fun foldRight() {
            items.foldRight(3) { total, n ->
                total + n
            }
        }


        /**
         * 遍历所有元素，并执行给定的操作。
         */
        fun forEach() {

            items.forEach {
                println(it)
            }
        }


        fun forEachIndex() {

            items.forEachIndexed { index, value ->

                println("$index  ==> $value")

            }
        }

        /**
         * 返回最大的一项，如果没有则返回null。
         */
        fun maxList() {

            val max = items.max()

            println(max)


        }


        /**
         * 根据给定的函数返回最大的一项，如果没有则返回null。
         */
        fun maxByList() {

            items.maxBy { it + 1.minus(12) }

        }


        /**
         * 如果没有任何元素与给定的函数匹配，则返回true。
         */
        fun noneFun() {

            items.none { it % 3 == 0 }

        }

        /**
         * 与 fold 一样，但是没有一个初始值。通过一个函数从第一项到最后一项进行累
        计。
         */
        fun reduceFun() {

            items.reduce { acc, i -> acc + i }

        }


        //返回所有每一项通过函数转换之后的数据的总和
        fun sumBy() {

            items.sumBy { it % 2 }


        }
    }


    //过滤操作符
    class FliterOperator {

        var list = listOf(2, 2, 56, 5, 48)


        /**
         * 返回包含去掉前n个元素的后的所有元素的列表
         */
        fun dropFun() {


            val drop = list.drop(2)


            drop.forEach { println(it) }

        }

    }

}