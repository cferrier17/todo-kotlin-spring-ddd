package com.example.todokotlinspringddd.domain

import org.springframework.stereotype.Service
import java.util.*

@Service
class TodoService(private val todoPersistence: TodoPersistence) : TodoManagement {
    var rank = 0

    override fun saveTodo(request: TodoCreationRequest): Todo {
        rank++

        return todoPersistence.saveTodo(
            Todo(
                id = UUID.randomUUID().toString(),
                title = request.title,
                completed = false,
                rank = rank
            )
        )
    }

    override fun getAllTodos(): List<Todo> {
        return todoPersistence.getAllTodo()
            .sortedBy(Todo::rank)
    }

    override fun deleteTodos(completed: Boolean?) {
        TODO("Not yet implemented")
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