package com.example.todokotlinspringddd.user

import com.example.todokotlinspringddd.domain.TodoCreationRequest
import com.example.todokotlinspringddd.domain.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class ApiAdapter(private val todoManagement: TodoService) {

    @PostMapping("/todos")
    fun createTodo(@RequestBody request: TodoCreationRequest): ResponseEntity<*> {
        val todo = todoManagement.saveTodo(request)

        val todoResponse = TodoResponse(todo)
        return ResponseEntity(todoResponse, HttpStatus.CREATED)
    }

    @GetMapping("/todos")
    fun getAllTodo(): ResponseEntity<List<TodoResponse>> {
        val todos = todoManagement.getAllTodos()

        val todoResponses = todos.map { todo -> TodoResponse(todo) }
        return ResponseEntity.ok(todoResponses)
    }

    @DeleteMapping("/todos")
    fun deleteAllTodo(): ResponseEntity<Nothing> {
        todoManagement.deleteTodos()

        return ResponseEntity(null, HttpStatus.NO_CONTENT)
    }
}