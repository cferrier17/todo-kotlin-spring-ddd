package com.example.todokotlinspringddd.domain

import java.util.*

interface TodoPersistence {
    fun saveTodo(todoDomain: TodoDomain): TodoDomain
    fun saveTodo(todoDomain: TodoDomainNullable): TodoDomain?

    fun getAllTodo(): List<TodoDomain>

    fun deleteAllTodo(completed: Boolean?)

    fun findTodo(id: String): Optional<TodoDomain>?

    fun updateTodoFull(id: String, todoDomain: TodoDomain): TodoDomain?

    fun todoExists(id: String) : Boolean

    fun rankIsFree(rank: Int) : Boolean
    fun deleteById(id: String)
}