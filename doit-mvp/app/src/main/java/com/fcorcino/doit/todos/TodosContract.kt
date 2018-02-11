package com.fcorcino.doit.todos

import com.fcorcino.doit.BasePresenter
import com.fcorcino.doit.BaseView
import com.fcorcino.doit.model.Todo
import android.view.View as AndroidView

interface TodosContract {
    interface View : BaseView<Presenter> {
        fun initUi(view: AndroidView)

        fun initListeners()

        fun clearTodoField()

        fun showTodos(todos: List<Todo>)
    }

    interface Presenter : BasePresenter {
        fun addTodo(todo: Todo)

        fun deleteTodo(todo: Todo)
    }
}