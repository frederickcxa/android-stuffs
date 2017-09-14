package com.fcorcino.shoppingcart.products

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import com.fcorcino.shoppingcart.R
import com.fcorcino.shoppingcart.utils.replaceFragment

class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val productsFragment: ProductsFragment? = supportFragmentManager.findFragmentById(R.id.fragment_container) as? ProductsFragment

        if (productsFragment == null) {
            replaceFragment(R.id.fragment_container, ProductsFragment())
        }
    }
}
