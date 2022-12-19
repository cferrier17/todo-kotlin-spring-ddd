package com.example.todokotlinspringddd.user

import com.example.todokotlinspringddd.domain.Todo

class TodoResponse(
    var id: String? = null,
    var title: String,
    var completed: Boolean,
    var order: Int,
    var url: String
) {

    constructor(todo: Todo) : this(todo.id, todo.title, todo.completed, todo.rank, "url")
}