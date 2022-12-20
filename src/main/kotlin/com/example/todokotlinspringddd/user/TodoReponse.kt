package com.example.todokotlinspringddd.user

import com.example.todokotlinspringddd.domain.TodoDomain
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

class TodoResponse(
    var id: String? = null,
    var title: String,
    var completed: Boolean,
    var order: Int,
    var url: String
) {


    constructor(todo: TodoDomain) : this(
        id = todo.id,
        title = todo.title,
        completed = todo.completed,
        order = todo.rank,
        url = String.format(
            "%s/todos/%s",
            ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString(),
            todo.id
        )
    )
}