package com.example.todokotlinspringddd.server

import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

interface TodoJpaRepository : CrudRepository<TodoJpa, String> {
    @Transactional
    fun deleteAllByCompletedIsTrue()

    fun existsByRank(rank: Int): Boolean
}