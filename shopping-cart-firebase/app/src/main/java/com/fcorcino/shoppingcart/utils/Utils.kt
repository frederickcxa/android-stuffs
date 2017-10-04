package com.fcorcino.shoppingcart.utils

import android.app.Activity
import android.support.v4.app.Fragment
import android.widget.Toast
import com.fcorcino.shoppingcart.model.Product

fun Float.asPrice(digits: Int = 2): String = java.lang.String.format("$%,.${digits}f", this)

fun Activity.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun Fragment.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    activity.toast(message, length)
}

fun getMockData(): List<Product> = listOf(
        Product(
                1,
                "Cerveza Presidente",
                "La mejor cerveza Dominicana",
                100F,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSVCX9_q-cxuAfshDvx8grc71tIGZFqGA1YcrN7hkFJFFptgdXThg",
                "",
                1),
        Product(
                2,
                "Ron Barceló",
                "Calidad que se siente",
                385F,
                "https://cdn2.bigcommerce.com/server5500/tpbc2s65/products/2386/images/2419/ronbarcelo_gran_anejo__00321__28844.1358534405.380.500.jpg?c=2",
                "",
                1

        ),
        Product(
                3,
                "Ron Brugal",
                "El sabor del ron",
                475F,
                "https://images-na.ssl-images-amazon.com/images/I/81ILWbqitxL._SY445_.jpg",
                "",
                1

        ),
        Product(
                4,
                "Pepsi",
                "Rompe la rutina",
                60F,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ43eUZNFSpxWjoo7My0JTzhMlv7BbHTbBzQgkIMsozqLDKaYDd",
                "",
                1
        )
)
