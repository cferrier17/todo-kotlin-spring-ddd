package com.example.todokotlinspringddd.server

import com.example.todokotlinspringddd.domain.TodoDomain
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

@DataJpaTest
class TodoJpaRepositoryTest @Autowired constructor(
    var repository: TodoJpaRepository,
    var entityManager: TestEntityManager
) {

    @Test
    fun `should delete only completed todos`() {
        entityManager.persist(
            TodoJpa(
                id = UUID.randomUUID().toString(),
                title = "title",
                completed = true,
                rank = 10
            )
        )

        entityManager.persist(
            TodoJpa(
                id = UUID.randomUUID().toString(),
                title = "title",
                completed = false,
                rank = 20
            )
        )

        repository.deleteAllByCompletedIsTrue()

        var todos = repository.findAll().iterator().asSequence().toList()

        assertThat(todos.size).isEqualTo(1)
        assertThat(todos.get(0).completed).isFalse()
    }

    @Test
    fun `should return true if rank exists`() {
        entityManager.persist(
            TodoJpa(
                id = UUID.randomUUID().toString(),
                title = "title",
                completed = true,
                rank = 10
            )
        )

        val existsByRank = repository.existsByRank(10)
        val notExistsByRank = repository.existsByRank(5)

        assertThat(existsByRank).isTrue()
        assertThat(notExistsByRank).isFalse()


    }
}