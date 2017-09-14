package com.fcorcino.shoppingcart.products

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.fcorcino.shoppingcart.data.DataSource
import com.fcorcino.shoppingcart.data.Repository
import com.fcorcino.shoppingcart.model.Product

class ProductsViewModel : ViewModel() {
    private val repository = Repository.instance
    lateinit var products: LiveData<ArrayList<Product>>
    var displayProgress: MutableLiveData<Boolean> = MutableLiveData()
    var displayError: MutableLiveData<Boolean> = MutableLiveData()

    fun init() {
        displayProgress.value = true
        products = repository.getProducts(object : DataSource.GetDataCallback {
            override fun onFailure() {
                displayError.postValue(true)
            }

            override fun onFinish() {
                displayProgress.postValue(false)
            }
        })
    }
}
