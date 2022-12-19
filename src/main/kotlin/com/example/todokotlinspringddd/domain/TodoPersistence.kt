package com.example.todokotlinspringddd.domain

interface TodoPersistence {
    fun saveTodo(todoDomain : TodoDomain): TodoDomain

    fun getAllTodo(): List<TodoDomain>
}