package com.fcorcino.doit.todos

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fcorcino.doit.R
import com.fcorcino.doit.extensions.replaceFragment
import kotlinx.android.synthetic.main.toolbar.*

class TodosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        setContentView(R.layout.activity_todos)
        setSupportActionBar(toolbar)

        val todosFragment = supportFragmentManager.findFragmentById(R.id.todosFragmentContainer) as? TodosFragment

        if (todosFragment == null) replaceFragment(R.id.todosFragmentContainer, TodosFragment())
    }
}
