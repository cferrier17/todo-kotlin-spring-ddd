package com.example.todokotlinspringddd.server

import com.example.todokotlinspringddd.domain.Todo
import com.example.todokotlinspringddd.domain.TodoPersistence
import org.springframework.data.repository.CrudRepository

interface TodoRepository : CrudRepository<Todo, String>