package com.fcorcino.shoppingcart.products

import com.fcorcino.shoppingcart.model.CustomCallback
import com.fcorcino.shoppingcart.model.Product
import com.fcorcino.shoppingcart.utils.ApiUtils
import com.fcorcino.shoppingcart.utils.JsonKeys
import org.json.JSONObject
import java.io.IOException

class ProductsPresenter(val view: ProductsContract.View) : ProductsContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadProducts()
    }

    override fun loadProducts() {
        ApiUtils.getProducts().enqueue(object : CustomCallback() {
            override fun onFailure(ex: IOException) {
                view.showGetProductsErrorMessage()
            }

            override fun onSuccess(response: JSONObject) {
                val products = arrayListOf<Product>()

                response.getJSONArray(JsonKeys.data).also {
                    (0 until it.length()).mapTo(products) { index ->
                        Product(it.getJSONObject(index))
                    }.also {
                        view.showProducts(it)
                    }
                }
            }

            override fun onFinish() {
                view.displayLoader(false)
            }
        })
    }
}
