package com.example

import kotlin.math.max

class Person(var money: Double) {

    fun enoughMoney(toSpend: Double): Boolean{
        return toSpend <= money
    }

    fun spendMoney(toSpend: Double) {
        money = max(money - toSpend, 0.0)
    }
}