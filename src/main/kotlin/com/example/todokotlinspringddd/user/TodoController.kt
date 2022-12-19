package com.example.todokotlinspringddd.user

import com.example.todokotlinspringddd.domain.Todo
import com.example.todokotlinspringddd.domain.TodoCreationRequest
import com.example.todokotlinspringddd.domain.TodoService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller()
class TodoController(private val todoManagement: TodoService) {

    @PostMapping("/todos")
    fun createTodo(@RequestBody request: TodoCreationRequest): ResponseEntity<*> {
        val todo = todoManagement.saveTodo(request)

        val todoResponse = TodoResponse(todo)
        return ResponseEntity.ok(todoResponse)
    }

    @GetMapping("/todos")
    fun getAllTodo(): ResponseEntity<List<TodoResponse>> {
        val todos = todoManagement.getAllTodos()

        val todoResponses = todos.map { todo -> TodoResponse(todo) }
        return ResponseEntity.ok(todoResponses)
    }
}