package com.fcorcino.shoppingcart.model

import org.json.JSONObject

data class Product(private val json: JSONObject) {
    val productId: Int
        get() = json.getInt("productId")
    val name: String
        get() = json.getString("name")
    val description: String
        get() = json.getString("description")
    val price: Float
        get() = json.getDouble("price").toFloat()
    val image: String
        get() = json.getString("image")
    val thumbnail: String
        get() = json.optString("thumbnail", "")
}
