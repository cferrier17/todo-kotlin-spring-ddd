package com.example.todokotlinspringddd.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Todo (
    @Id var id: String? = null,
    var title: String,
    var completed: Boolean,
    var rank: Int,
)