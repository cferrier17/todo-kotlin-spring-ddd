package com.example.todokotlinspringddd.domain

interface TodoPersistence {
    fun saveTodo(todo: Todo): Todo
}