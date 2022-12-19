package com.example.todokotlinspringddd.server

import com.example.todokotlinspringddd.domain.TodoDomain
import org.springframework.stereotype.Service

@Service
class TodoMapper {
    fun todoJpaToTodoDomain(todoJpa: TodoJpa): TodoDomain {
        return TodoDomain(
            id = todoJpa.id,
            title = todoJpa.title,
            completed = todoJpa.completed,
            rank = todoJpa.rank
        )
    }

    fun todoDomainToTodoJpa(todoDomain: TodoDomain): TodoJpa {
        return TodoJpa(
            id = todoDomain.id,
            title = todoDomain.title,
            completed = todoDomain.completed,
            rank = todoDomain.rank
        )
    }
}