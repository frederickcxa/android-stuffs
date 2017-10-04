package com.fcorcino.shoppingcart.products

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log

import com.fcorcino.shoppingcart.R
import com.fcorcino.shoppingcart.utils.getMockData
import com.fcorcino.shoppingcart.utils.replaceFragment
import com.google.firebase.firestore.FirebaseFirestore

class ProductsActivity : AppCompatActivity() {
    companion object {
        val TAG : String = ProductsActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        init()
    }

    private fun initUI() {
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val productsFragment: ProductsFragment? = supportFragmentManager.findFragmentById(R.id.fragment_container) as? ProductsFragment

        if (productsFragment == null) {
            replaceFragment(R.id.fragment_container, ProductsFragment())
        }
    }

    private fun init() {
        val fireStore = FirebaseFirestore.getInstance()
        fireStore.collection("products")
                .get()
                .addOnCompleteListener { if (it.isSuccessful) it.result.forEach { product -> product.reference.delete() }  }
        getMockData().forEach {
            fireStore.collection("products")
                    .add(it)
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot added with ID: ${it.id}") }
                    .addOnFailureListener { Log.e(TAG, "Could not add product", it)  }
                    .addOnCompleteListener { Log.e(TAG, "The task was: completed: ${it.isComplete}, successful: ${it.isSuccessful}") }
        }
    }
}
