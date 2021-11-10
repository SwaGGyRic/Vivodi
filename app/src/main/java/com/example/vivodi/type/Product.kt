package com.example.vivodi.type

import java.math.BigDecimal

open class Product(var Name:String, PriceArg:BigDecimal) {
    var Price = PriceArg
    open fun getInfo():String{
        return Name+" - "+Price
    }
}