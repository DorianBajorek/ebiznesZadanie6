package com.example

class Product(val name: String, val price: Double, val category: String, var amount: Int){

    fun isAvailable(): Boolean{
        return amount > 0;
    }

    fun decraseAmount(){
        amount = amount - 1
    }
}