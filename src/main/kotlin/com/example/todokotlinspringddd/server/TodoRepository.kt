package com.example.todokotlinspringddd.server

import com.example.todokotlinspringddd.domain.Todo
import org.springframework.data.repository.CrudRepository

interface TodoRepository : CrudRepository<Todo, String>