package com.fcorcino.doit.todos

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fcorcino.doit.R
import com.fcorcino.doit.extensions.clear
import com.fcorcino.doit.extensions.show
import com.fcorcino.doit.extensions.value
import com.fcorcino.doit.model.Todo
import kotlinx.android.synthetic.main.fragment_todo.*
import kotlin.properties.Delegates
import android.support.v7.widget.DividerItemDecoration
import android.widget.ImageView
import com.fcorcino.doit.extensions.onTextChange

class TodosFragment : Fragment() {
    private lateinit var todosAdapter: TodosAdapter
    private var todos by Delegates.observable(listOf<Todo>()) { _, _, todos ->
        todosAdapter.swapData(todos)
        emptyListText.show(todos.isEmpty())
    }

    private var isTodoValid by Delegates.observable(false) { _, _, isTodoValid ->
        with(addTodoFab) {
            isEnabled = isTodoValid
            show(isTodoValid)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_todo, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initListeners()
    }

    private fun initUI() {
        todosAdapter = TodosAdapter(listOf(), ::deleteTodo)

        with(todosRecyclerView) {
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = todosAdapter
            val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun initListeners() {
        addTodoFab.setOnClickListener { addTodo(addTodoField.value) }
        addTodoField.onTextChange(::handleTodoValidity)
    }

    private fun handleTodoValidity(description: CharSequence?) {
        isTodoValid = description?.isNotEmpty() == true
    }

    private fun addTodo(description: String) {
        val id = todos.size + 1
        todos += Todo(id, description)
        addTodoField.clear()
    }

    private fun deleteTodo(id: Int) {
        todos = todos.filter { todo -> todo.id != id }
    }

    class TodosAdapter(private var todo: List<Todo>, val deleteHandler: (Int) -> Unit) : RecyclerView.Adapter<TodosAdapter.TodoViewHolder>() {
        override fun getItemCount() = todo.size

        override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
            holder.bind(todo[position])
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false))

        fun swapData(todo: List<Todo>) {
            this.todo = todo
            notifyDataSetChanged()
        }

        inner class TodoViewHolder(item: View) : RecyclerView.ViewHolder(item) {
            private val todoDescriptionText: TextView = item.findViewById(R.id.todoDescriptionText)
            private val deleteTodoIcon: ImageView = item.findViewById(R.id.deleteTodoIcon)

            fun bind(todo: Todo) {
                with(todo) {
                    todoDescriptionText.text = description
                    deleteTodoIcon.setOnClickListener { deleteHandler(id) }
                }
            }
        }
    }
}
