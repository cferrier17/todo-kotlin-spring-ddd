package com.example.todokotlinspringddd.domain

class TodoDomain(
    var id: String? = null,
    var title: String,
    var completed: Boolean,
    var rank: Int
)

class TodoDomainNullable(
    var id: String,
    var title: String?,
    var completed: Boolean?,
    var rank: Int?
)