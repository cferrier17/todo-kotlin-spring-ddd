package com.example.todokotlinspringddd.user

import com.example.todokotlinspringddd.domain.TodoDomain

class TodoResponse(
    var id: String? = null,
    var title: String,
    var completed: Boolean,
    var order: Int,
    var url: String
) {

    constructor(todo: TodoDomain) : this(todo.id, todo.title, todo.completed, todo.rank, "url")
}