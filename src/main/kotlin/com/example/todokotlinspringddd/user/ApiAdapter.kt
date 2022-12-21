package com.example.todokotlinspringddd.user

import com.example.todokotlinspringddd.domain.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/todos")
class ApiAdapter(private val todoManagement: TodoService, private val mapper: RequestMapper) {

    @PostMapping
    fun createTodo(@RequestBody requestApi: TodoCreationRequestApi): ResponseEntity<*> {
        val requestDomain = mapper.creationRequestApiToDomain(requestApi)
        val todo = todoManagement.saveTodo(requestDomain)

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
        @RequestBody requestApi: TodoFullUpdateRequestApi
    ): ResponseEntity<TodoResponse> {

        val (todoExists, orderIsFree) = todoManagement.isUpdatable(id, requestApi.rank)

        if (!todoExists) {
            return ResponseEntity.notFound().build()
        }
        if (orderIsFree != null && orderIsFree) {
            return ResponseEntity(null, HttpStatus.CONFLICT)
        }

        val requestDomain = mapper.fullUpdateRequestApiToDomain(requestApi)
        val todo = todoManagement.fullUpdateTodo(id, requestDomain)
        val response = TodoResponse(todo)
        return ResponseEntity.ok(response)
    }


    @PatchMapping("/{id}")
    fun updateTodoPartial(
        @PathVariable(name = "id") id: String,
        @RequestBody requestApi: TodoPartialUpdateRequestApi
    ): ResponseEntity<TodoResponse> {

        val (todoExists, orderIsFree) = todoManagement.isUpdatable(id, requestApi.rank)

        if (!todoExists) {
            return ResponseEntity.notFound().build()
        }
        if (orderIsFree != null && orderIsFree) {
            return ResponseEntity(null, HttpStatus.CONFLICT)
        }


        val requestDomain = mapper.partialUpdateRequestApiToDomain(requestApi)
        val todo = todoManagement.partialUpdateTodo(id, requestDomain) ?: return ResponseEntity.notFound().build()
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