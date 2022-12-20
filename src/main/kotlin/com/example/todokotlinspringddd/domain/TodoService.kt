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
        return todoPersistence.deleteById(id)
    }

    override fun getTodo(id: String): Optional<TodoDomain>? {
        return todoPersistence.findTodo(id)
    }

    override fun partialUpdateTodo(request: TodoPartialUpdateRequest) {
        TODO("Not yet implemented")
    }

    override fun fullUpdateTodo(id: String, request: TodoUpdateRequest): TodoDomain {
        return todoPersistence.saveTodo(
            TodoDomain(
                id = id,
                title = request.title,
                completed = request.completed,
                rank = request.rank
            )
        )
    }

//    override fun partialUpdateTodo(id: String, request: TodoPartialUpdateRequest) : TodoDomain{
//
//
//        return todoPersistence.saveTodo(TodoDomain(
//            id = id,
//            title = request.title,
//            completed = request.completed,
//            rank = request.order
//        ))
//    }

    override fun todoExists(id: String): Boolean {
        return todoPersistence.todoExists(id)
    }

    override fun isUpdatable(id: String, rank: Int): Pair<Boolean, Boolean> {
        val todoExists = todoExists(id)
        val rankExists = !todoPersistence.rankExists(rank)

        return Pair(todoExists, rankExists)
    }


}