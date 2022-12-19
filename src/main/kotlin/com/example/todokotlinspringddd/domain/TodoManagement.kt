package com.example.todokotlinspringddd.domain

interface TodoManagement {
    fun saveTodo(request: TodoCreationRequest): Todo

    fun getAllTodos()

    fun deleteTodos(completed: Boolean?)

    fun deleteTodo(id: String)

    fun getTodo(id: String)

    fun partialUpdateTodo(request: TodoPartialUpdateRequest)

    fun fullUpdateTodo(request: TodoUpdateRequest)

}