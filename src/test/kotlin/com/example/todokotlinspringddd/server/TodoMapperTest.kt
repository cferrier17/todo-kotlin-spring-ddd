package com.example.todokotlinspringddd.server

import com.example.todokotlinspringddd.domain.TodoDomain
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class TodoMapperTest @Autowired constructor(var todoMapper: TodoMapper) {

    @Test
    fun `should map a domain todo to a jpa todo`() {
        val todoDomain = TodoDomain(
            id = UUID.randomUUID().toString(),
            title = "title",
            completed = true,
            rank = 10
        )
        val todoJpa = todoMapper.todoDomainToTodoJpa(todoDomain)

        assertThat(todoJpa.id).isEqualTo(todoDomain.id)
        assertThat(todoJpa.title).isEqualTo(todoDomain.title)
        assertThat(todoJpa.completed).isEqualTo(todoDomain.completed)
        assertThat(todoJpa.rank).isEqualTo(todoDomain.rank)

    }

    @Test
    fun `should map a jpa todo to a domain todo`() {
        val todoJpa = TodoJpa(
            id = UUID.randomUUID().toString(),
            title = "title",
            completed = true,
            rank = 10
        )
        val todoDomain = todoMapper.todoJpaToTodoDomain(todoJpa)

        assertThat(todoDomain.id).isEqualTo(todoJpa.id)
        assertThat(todoDomain.title).isEqualTo(todoJpa.title)
        assertThat(todoDomain.completed).isEqualTo(todoJpa.completed)
        assertThat(todoDomain.rank).isEqualTo(todoJpa.rank)

    }
}