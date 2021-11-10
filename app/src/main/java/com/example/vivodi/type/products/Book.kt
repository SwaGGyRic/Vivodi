package com.example.vivodi.type.products

import com.example.vivodi.type.Product
import java.math.BigDecimal

class Book(name:String, price:BigDecimal, var pageCount:Int):Product(name, price) {
    override fun getInfo(): String {
        return super.getInfo()+" pages: "+pageCount
    }
}