package com.example.todokotlinspringddd.user

import com.example.todokotlinspringddd.domain.TodoCreationRequest
import com.example.todokotlinspringddd.domain.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/todos")
class ApiAdapter(private val todoManagement: TodoService) {

    @PostMapping
    fun createTodo(@RequestBody request: TodoCreationRequest): ResponseEntity<*> {
        val todo = todoManagement.saveTodo(request)

        val todoResponse = TodoResponse(todo)
        return ResponseEntity(todoResponse, HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllTodo(): ResponseEntity<List<TodoResponse>> {
        val todos = todoManagement.getAllTodos()

        val todoResponses = todos.map { todo -> TodoResponse(todo) }
        return ResponseEntity.ok(todoResponses)
    }

    @DeleteMapping
    fun deleteAllTodo(
        @PathVariable(
            required = false,
            name = "completed"
        ) completed: Boolean?
    ): ResponseEntity<Nothing> {
        todoManagement.deleteTodos(completed)
        return ResponseEntity(null, HttpStatus.NO_CONTENT)
    }
}