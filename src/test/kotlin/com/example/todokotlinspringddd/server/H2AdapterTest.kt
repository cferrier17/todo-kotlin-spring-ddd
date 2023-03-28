package com.example.todokotlinspringddd.server

import com.example.todokotlinspringddd.domain.TodoDomain
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import java.util.*

@SpringBootTest //todo : test intégration uniquement
class H2AdapterTest @Autowired constructor(
    var repository: TodoJpaRepository, //todo : à mocker, à tester avec verify
    var todoMapper: TodoMapper,
    var h2Adapater: H2Adapter
) {

    @Test
    fun `should save a todo domain to a todoJpa`() {
        val id = UUID.randomUUID().toString()
        val todoDomain = TodoDomain(
            id = id,
            title = "title",
            completed = true,
            rank = 10
        )

        val savedTodo = h2Adapater.saveTodo(todoDomain)

        assertThat(savedTodo.id).isEqualTo(savedTodo.id)
    }

    @Test
    fun `should save a nullable todo domain to a todoJpa`() {
        val id = UUID.randomUUID().toString()
        val todoDomain = TodoDomain(
            id = id,
            title = "title",
            completed = true,
            rank = 10
        )

        val savedTodo = h2Adapater.saveTodo(todoDomain)

        assertThat(savedTodo.id).isEqualTo(savedTodo.id)
    }

    @Test
    fun `should return all todo jpa as todo domain`() {
        h2Adapater.saveTodo(
            TodoDomain(
                id = UUID.randomUUID().toString(),
                title = "title",
                completed = true,
                rank = 10
            )
        )

        val allTodo = h2Adapater.getAllTodo()
        assertThat(allTodo.get(0).javaClass).isEqualTo(TodoDomain::class.java)
    }

    @Test
    fun `should delete all todo`() {
        h2Adapater.saveTodo(
            TodoDomain(
                id = UUID.randomUUID().toString(),
                title = "title",
                completed = true,
                rank = 10
            )
        )

        h2Adapater.deleteAllTodo(null)
        val allTodo = h2Adapater.getAllTodo()
        assertThat(allTodo).isEmpty()
    }

    @Test
    fun `should delete all completed todo`() {
        h2Adapater.saveTodo(
            TodoDomain(
                id = UUID.randomUUID().toString(),
                title = "title",
                completed = true,
                rank = 10
            )
        )

        h2Adapater.saveTodo(
            TodoDomain(
                id = UUID.randomUUID().toString(),
                title = "title",
                completed = false,
                rank = 5
            )
        )

        h2Adapater.deleteAllTodo(true)
        val allTodo = h2Adapater.getAllTodo()
        assertThat(allTodo.size).isEqualTo(1)
        assertThat(allTodo.get(0).completed).isFalse()
    }


    @Test
    fun `should retrieve a todo by its id`() {
        val id = UUID.randomUUID().toString()
        h2Adapater.saveTodo(
            TodoDomain(
                id = id,
                title = "title",
                completed = true,
                rank = 10
            )
        )

        val todo = h2Adapater.findTodo(id)

        assertThat(todo).isPresent
        assertThat(todo.get().id).isEqualTo(id)
    }

    @Test
    fun `should delete a todo by its id`() {
        val id = UUID.randomUUID().toString()
        h2Adapater.saveTodo(
            TodoDomain(
                id = id,
                title = "title",
                completed = true,
                rank = 10
            )
        )

        h2Adapater.deleteById(id)
        val todo = h2Adapater.findTodo(id)

        assertThat(todo).isEmpty
    }
}

