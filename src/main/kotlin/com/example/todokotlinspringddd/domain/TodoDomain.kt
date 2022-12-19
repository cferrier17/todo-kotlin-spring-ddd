package com.example.todokotlinspringddd.domain

class TodoDomain(
    var id: String? = null,
    var title: String,
    var completed: Boolean,
    var rank: Int
)