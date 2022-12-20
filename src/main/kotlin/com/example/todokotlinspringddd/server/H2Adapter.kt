package com.example.todokotlinspringddd.server

import com.example.todokotlinspringddd.domain.TodoDomain
import com.example.todokotlinspringddd.domain.TodoDomainNullable
import com.example.todokotlinspringddd.domain.TodoPersistence
import org.springframework.stereotype.Service
import java.util.*

@Service
class H2Adapter(
    private val repository: TodoJpaRepository,
    private val todoMapper: TodoMapper
) : TodoPersistence {

    override fun saveTodo(todoDomain: TodoDomain): TodoDomain {
        val todoJpa = todoMapper.todoDomainToTodoJpa(todoDomain)
        val savedJpa = repository.save(todoJpa)

        return todoMapper.todoJpaToTodoDomain(savedJpa)
    }

    override fun saveTodo(todoDomain: TodoDomainNullable): TodoDomain? {
        val todoInBase = findTodo(todoDomain.id)

        if (todoInBase.isPresent) {
            var todo = todoInBase.get()

            if (todoDomain.title != null) {
                todo.title = todoDomain.title!!
            }

            if (todoDomain.completed != null) {
                todo.completed = todoDomain.completed!!
            }

            if (todoDomain.rank != null) {
                todo.rank = todoDomain.rank!!
            }

            return saveTodo(todo)
        }

        return null
    }

    override fun getAllTodo(): List<TodoDomain> {
        return repository.findAll()
            .map { todoJpa -> todoMapper.todoJpaToTodoDomain(todoJpa) }
    }

    override fun deleteAllTodo(completed: Boolean?) {
        if (completed == null) {
            repository.deleteAll()
        } else {
            repository.deleteAllByCompletedIsTrue()
        }

    }

    override fun findTodo(id: String): Optional<TodoDomain> {
        return repository.findById(id).map { todoJpa -> todoMapper.todoJpaToTodoDomain(todoJpa) }
    }

    override fun updateTodoFull(id: String, todoDomain: TodoDomain): TodoDomain? {
        return null
    }

    override fun todoExists(id: String): Boolean {
        return repository.existsById(id)
    }

    override fun rankIsFree(rank: Int): Boolean {
        return !repository.existsByRank(rank)
    }

    override fun deleteById(id: String) {
        return repository.deleteById(id)
    }

}