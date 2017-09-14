package com.fcorcino.shoppingcart.data

import android.arch.lifecycle.LiveData
import com.fcorcino.shoppingcart.model.CustomCallback
import com.fcorcino.shoppingcart.model.Product
import com.fcorcino.shoppingcart.utils.*
import org.json.JSONObject
import java.io.IOException
import android.arch.lifecycle.MutableLiveData

class Repository : DataSource {
    companion object {
        val instance by lazy { Repository() }
    }

    override fun getProducts(callback: DataSource.GetDataCallback): LiveData<ArrayList<Product>> {
        val data = MutableLiveData<ArrayList<Product>>()

        ApiUtils.getProducts().enqueue(object : CustomCallback() {
            override fun onFailure(ex: IOException) {
                callback.onFailure()
            }

            override fun onSuccess(response: JSONObject) {
                val products = arrayListOf<Product>()

                response.getJSONArray(JsonKeys.data).also {
                    (0 until it.length()).mapTo(products) { index ->
                        Product(it.getJSONObject(index))
                    }.also {
                        data.postValue(it)
                    }
                }
            }

            override fun onFinish() {
                callback.onFinish()
            }
        })

        return data
    }
}
