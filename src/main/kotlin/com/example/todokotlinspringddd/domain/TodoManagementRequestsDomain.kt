package com.example.todokotlinspringddd.domain


class TodoCreationRequestDomain(
    var title: String
)

class TodoFullUpdateRequestDomain(
    var title: String,
    var completed: Boolean,
    var rank: Int
)

class TodoPartialUpdateRequestDomain(
    var title: String?,
    var completed: Boolean?,
    var rank: Int?
)