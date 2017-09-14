package com.fcorcino.shoppingcart.products

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import com.fcorcino.shoppingcart.R
import com.fcorcino.shoppingcart.model.Product
import com.fcorcino.shoppingcart.utils.*


class ProductsFragment : LifecycleFragment() {
    private lateinit var productAdapter: ProductAdapter
    private lateinit var progressBar: ProgressBar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindObservers()
    }

    private fun bindObservers() {
        ViewModelProviders.of(this).get(ProductsViewModel::class.java).apply {
            init()
            products.observe(this@ProductsFragment, Observer { products -> products?.let { productAdapter.swapData(it) } })
            displayProgress.observe(this@ProductsFragment, Observer { displayProgress -> progressBar.show(displayProgress ?: false) })
            displayError.observe(this@ProductsFragment, Observer { toast(getString(R.string.products_not_available_error)) })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productAdapter = ProductAdapter(arrayListOf())

        with(view.findViewById<RecyclerView>(R.id.recycler_products)) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = productAdapter
        }

        progressBar = view.findViewById(R.id.progressBar)
    }

    class ProductAdapter(private val products: ArrayList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
        override fun getItemCount() = products.size

        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            with(holder) {
                val product = products[position]
                textProductName.text = product.name
                textProductPrice.text = product.price.asPrice()
                imageProductImage.load(product.image)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))

        fun swapData(newProducts: ArrayList<Product>) {
            products.clear()
            products.addAll(newProducts)
            notifyDataSetChanged()
        }

        class ProductViewHolder(item: View) : RecyclerView.ViewHolder(item) {
            val textProductName: TextView = item.findViewById(R.id.textProductName)
            val textProductPrice: TextView = item.findViewById(R.id.textProductPrice)
            val imageProductImage: ImageView = item.findViewById(R.id.imageProductImage)
        }
    }
}
