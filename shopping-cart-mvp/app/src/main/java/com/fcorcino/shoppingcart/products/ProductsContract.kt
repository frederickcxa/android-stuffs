package com.fcorcino.shoppingcart.products

import com.fcorcino.shoppingcart.BasePresenter
import com.fcorcino.shoppingcart.BaseView
import com.fcorcino.shoppingcart.model.Product

interface ProductsContract {

    interface View : BaseView<Presenter> {

        fun initUI()

        fun showGetProductsErrorMessage()

        fun displayLoader(display: Boolean)

        fun showProducts(products: List<Product>)
    }

    interface Presenter : BasePresenter {

        fun loadProducts()
    }
}
