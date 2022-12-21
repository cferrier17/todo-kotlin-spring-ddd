package com.example.todokotlinspringddd.server

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface TodoJpaRepository : CrudRepository<TodoJpa, String> {
    @Transactional
    fun deleteAllByCompletedIsTrue()

    fun existsByRank(rank: Int): Boolean
}