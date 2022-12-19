package com.example.todokotlinspringddd.server

import com.example.todokotlinspringddd.domain.TodoDomain
import com.example.todokotlinspringddd.domain.TodoPersistence
import org.springframework.stereotype.Service

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

}