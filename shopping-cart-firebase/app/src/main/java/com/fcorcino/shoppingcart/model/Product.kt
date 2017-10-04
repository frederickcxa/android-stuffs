package com.fcorcino.shoppingcart.model

data class Product(
        val productId: Int,
        val name: String,
        val description: String,
        val price: Float,
        val image: String,
        val thumbnail: String,
        val status: Int
)
