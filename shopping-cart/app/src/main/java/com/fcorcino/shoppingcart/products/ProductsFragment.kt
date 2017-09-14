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
import com.fcorcino.shoppingcart.model.CustomCallback
import com.fcorcino.shoppingcart.model.Product
import com.fcorcino.shoppingcart.utils.*
import org.json.JSONObject
import java.io.IOException

class ProductsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productAdapter = ProductAdapter(arrayListOf())

        with(view.findViewById<RecyclerView>(R.id.recycler_products)) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = productAdapter
        }

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar).apply {
            this.show()
        }

        ApiUtils.getProducts().enqueue(object : CustomCallback() {
            override fun onFailure(ex: IOException) {
                toast(getString(R.string.products_not_available_error))
            }

            override fun onSuccess(response: JSONObject) {
                val products = arrayListOf<Product>()

                response.getJSONArray(JsonKeys.data).also {
                    (0 until it.length()).mapTo(products) { index ->
                        Product(it.getJSONObject(index))
                    }.also {
                        if (isAdded) activity.runOnUiThread { productAdapter.swapData(it) }
                    }
                }
            }

            override fun onFinish() {
                if (isAdded) activity.runOnUiThread { progressBar.hide() }
            }
        })
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
