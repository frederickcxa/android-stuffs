package com.fcorcino.shoppingcart.products

import android.support.v4.app.Fragment
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

class ProductsFragment : Fragment(), ProductsContract.View {
    lateinit var progressBar: ProgressBar
    lateinit var productAdapter: ProductAdapter
    lateinit var productPresenter: ProductsContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        displayLoader(true)
    }

    override fun onResume() {
        super.onResume()
        productPresenter.start()
    }

    override fun setPresenter(presenter: ProductsContract.Presenter) {
        productPresenter = presenter
    }

    override fun initUI() {
        view?.let {
            productAdapter = ProductAdapter(arrayListOf())
            progressBar = it.findViewById(R.id.progressBar)

            with(it.findViewById<RecyclerView>(R.id.recycler_products)) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = productAdapter
            }
        }
    }

    override fun showGetProductsErrorMessage() {
        runOnUI { toast(getString(R.string.products_not_available_error)) }
    }

    override fun displayLoader(display: Boolean) {
        runOnUI { progressBar.show(display) }
    }

    override fun showProducts(products: List<Product>) {
        runOnUI { productAdapter.swapData(products) }
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

        fun swapData(newProducts: List<Product>) {
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
