package com.example.todokotlinspringddd.domain

import java.util.*

interface TodoManagement {
    fun saveTodo(request: TodoCreationRequestDomain): TodoDomain

    fun getAllTodos(): List<TodoDomain>

    fun deleteTodos(completed: Boolean? = null)

    fun deleteTodo(id: String)

    fun getTodo(id: String): Optional<TodoDomain>?

//    fun partialUpdateTodo(id: String, request: TodoPartialUpdateRequest): TodoDomain

    fun fullUpdateTodo(id: String, request: TodoFullUpdateRequestDomain): TodoDomain

    fun isUpdatable(id: String, rank: Int?): Pair<Boolean, Boolean?>

    fun todoExists(id: String): Boolean
    fun partialUpdateTodo(id: String, request: TodoPartialUpdateRequestDomain): TodoDomain?
}