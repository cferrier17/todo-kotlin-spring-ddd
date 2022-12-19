package com.example.todokotlinspringddd.domain

import java.util.*

interface TodoManagement {
    fun saveTodo(request: TodoCreationRequest): TodoDomain

    fun getAllTodos(): List<TodoDomain>

    fun deleteTodos(completed: Boolean? = null)

    fun deleteTodo(id: String)

    fun getTodo(id: String) : Optional<TodoDomain>?

    fun partialUpdateTodo(request: TodoPartialUpdateRequest)

    fun fullUpdateTodo(request: TodoUpdateRequest)

}