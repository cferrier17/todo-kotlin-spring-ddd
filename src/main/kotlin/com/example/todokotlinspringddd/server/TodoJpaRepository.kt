package com.example.todokotlinspringddd.server

import org.springframework.data.repository.CrudRepository

interface TodoJpaRepository : CrudRepository<TodoJpa, String>