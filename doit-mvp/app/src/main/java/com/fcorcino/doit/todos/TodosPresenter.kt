package com.fcorcino.doit.todos

import com.fcorcino.doit.model.Todo

class TodosPresenter(
        private val view: TodosContract.View
) : TodosContract.Presenter {
    private val todos = arrayListOf<Todo>()

    init {
        view.presenter = this
    }

    override fun start() {}

    override fun addTodo(todo: Todo) {
        todos.add(todo)
        view.clearTodoField()
        view.showTodos(todos)
    }

    override fun deleteTodo(todo: Todo) {
        todos.remove(todo)
        view.showTodos(todos)
    }
}