package com.example.todokotlinspringddd.user

import com.example.todokotlinspringddd.domain.TodoCreationRequest
import com.example.todokotlinspringddd.domain.TodoPartialUpdateRequest
import com.example.todokotlinspringddd.domain.TodoService
import com.example.todokotlinspringddd.domain.TodoUpdateRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

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
        @RequestParam(
            required = false,
            name = "completed"
        ) completed: Boolean?
    ): ResponseEntity<Nothing> {
        todoManagement.deleteTodos(completed)
        return ResponseEntity(null, HttpStatus.NO_CONTENT)
    }

    @GetMapping("/{id}")
    fun getTodo(@PathVariable(name = "id") id: String): ResponseEntity<TodoResponse> {
        val todo = todoManagement.getTodo(id)

        return if (todo?.isPresent == true) {
            val todoResponse = TodoResponse(todo.get())
            ResponseEntity.ok(todoResponse)
        } else {
            ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    fun updateTodoFull(
        @PathVariable(name = "id") id: String,
        @RequestBody request: TodoUpdateRequest
    ): ResponseEntity<TodoResponse> {

        val (todoExists, orderIsFree) = todoManagement.isUpdatable(id, request.rank)

        if (!todoExists) {
            return ResponseEntity.notFound().build()
        }
        if (orderIsFree != null && orderIsFree) {
            return ResponseEntity(null, HttpStatus.CONFLICT)
        }

        val todo = todoManagement.fullUpdateTodo(id, request)
        val response = TodoResponse(todo)
        return ResponseEntity.ok(response)
    }


    @PatchMapping("/{id}")
    fun updateTodoPartial(
        @PathVariable(name = "id") id: String,
        @RequestBody request: TodoPartialUpdateRequest
    ): ResponseEntity<TodoResponse> {

        val (todoExists, orderIsFree) = todoManagement.isUpdatable(id, request.rank)

        if (!todoExists) {
            return ResponseEntity.notFound().build()
        }
        if (orderIsFree != null && orderIsFree) {
            return ResponseEntity(null, HttpStatus.CONFLICT)
        }

        val todo = todoManagement.partialUpdateTodo(id, request) ?: return ResponseEntity.notFound().build()
        val response = TodoResponse(todo)

        return ResponseEntity.ok(response)

    }

    @DeleteMapping("/{id}")
    fun deleteTodoById(@PathVariable(name = "id") id: String): ResponseEntity<Nothing> {
        if (todoManagement.todoExists(id)) {
            todoManagement.deleteTodo(id)
            return ResponseEntity(null, HttpStatus.NO_CONTENT)
        }

        return ResponseEntity.notFound().build()
    }
}