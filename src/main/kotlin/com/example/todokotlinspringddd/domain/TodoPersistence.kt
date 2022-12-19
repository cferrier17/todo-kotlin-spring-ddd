package com.example.todokotlinspringddd.domain

import java.util.*

interface TodoPersistence {
    fun saveTodo(todoDomain : TodoDomain): TodoDomain

    fun getAllTodo(): List<TodoDomain>

    fun deleteAllTodo(completed : Boolean?)

    fun findTodo(id : String) : Optional<TodoDomain>?
}