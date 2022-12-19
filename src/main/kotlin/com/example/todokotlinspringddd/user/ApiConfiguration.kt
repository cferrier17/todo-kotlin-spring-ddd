package com.example.todokotlinspringddd.user

import com.example.todokotlinspringddd.domain.TodoPersistence
import com.example.todokotlinspringddd.domain.TodoService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiConfiguration {
    @Bean
    fun TodoService(todoPersistenceBean: TodoPersistence): TodoService{
        return TodoService(todoPersistence = todoPersistenceBean)
    }
}