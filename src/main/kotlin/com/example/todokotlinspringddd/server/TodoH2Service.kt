package com.example.todokotlinspringddd.server

import com.example.todokotlinspringddd.domain.Todo
import com.example.todokotlinspringddd.domain.TodoPersistence
import org.springframework.stereotype.Service

@Service
class TodoH2Service(private val repository: TodoRepository) : TodoPersistence {
    override fun saveTodo(todo: Todo): Todo {
        return repository.save(todo)
    }

}