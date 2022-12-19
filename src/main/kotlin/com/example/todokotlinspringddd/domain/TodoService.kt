package com.example.todokotlinspringddd.domain

import java.util.*

class TodoService(private val todoPersistence: TodoPersistence) : TodoManagement {
    var rank = 0

    override fun saveTodo(request: TodoCreationRequest): TodoDomain {
        rank++

        return todoPersistence.saveTodo(
            TodoDomain(
                id = UUID.randomUUID().toString(),
                title = request.title,
                completed = false,
                rank = rank
            )
        )
    }

    override fun getAllTodos(): List<TodoDomain> {
        return todoPersistence.getAllTodo()
            .sortedBy(TodoDomain::rank)
    }

    override fun deleteTodos(completed: Boolean?) {
        todoPersistence.deleteAllTodo(completed)
    }

    override fun deleteTodo(id: String) {
        TODO("Not yet implemented")
    }

    override fun getTodo(id: String) {
        TODO("Not yet implemented")
    }

    override fun partialUpdateTodo(request: TodoPartialUpdateRequest) {
        TODO("Not yet implemented")
    }

    override fun fullUpdateTodo(request: TodoUpdateRequest) {
        TODO("Not yet implemented")
    }
}