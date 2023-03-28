package com.example.todokotlinspringddd.domain

import java.util.*

interface TodoManagement {
    fun saveTodo(request: TodoCreationRequestDomain): TodoDomain

    fun getAllTodos(): List<TodoDomain>

    fun deleteTodos(completed: Boolean? = null)

    fun deleteTodo(id: String)

    fun getTodo(id: String): Optional<TodoDomain>?

//    fun partialUpdateTodo(id: String, request: TodoPartialUpdateRequest): TodoDomain

    //todo : une seule fonction, sans patch
    fun fullUpdateTodo(id: String, request: TodoFullUpdateRequestDomain): TodoDomain

    //todo : delete
    fun isUpdatable(id: String, rank: Int?): Pair<Boolean, Boolean?>

    //todo : delete
    fun todoExists(id: String): Boolean

    //todo : une seule fonction, sans patch
    fun partialUpdateTodo(id: String, request: TodoPartialUpdateRequestDomain): TodoDomain?
}