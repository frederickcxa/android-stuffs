package com.fcorcino.shoppingcart.data

import android.arch.lifecycle.LiveData
import com.fcorcino.shoppingcart.model.Product

interface DataSource {

    interface GetDataCallback {

        fun onFailure()

        fun onFinish()
    }

    fun getProducts(callback: GetDataCallback): LiveData<ArrayList<Product>>
}
