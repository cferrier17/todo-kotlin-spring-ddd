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

    override fun partialUpdateTodo(id: String, request: TodoPartialUpdateRequest): TodoDomain? {
        return todoPersistence.saveTodo(
            TodoDomainNullable(
                id = id,
                title = request.title,
                completed = request.completed,
                rank = request.rank
            )
        )
    }

    override fun todoExists(id: String): Boolean {
        return todoPersistence.todoExists(id)
    }

    override fun isUpdatable(id: String, rank: Int?): Pair<Boolean, Boolean?> {
        val todoExists = todoExists(id)

        if (rank == null) {
            return Pair(todoExists, null)
        }

        val rankIsFree = !todoPersistence.rankIsFree(rank)
        return Pair(todoExists, rankIsFree)

    }


}