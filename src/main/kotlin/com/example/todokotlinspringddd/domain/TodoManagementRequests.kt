package com.example.todokotlinspringddd.domain

import com.fasterxml.jackson.annotation.JsonProperty


class TodoCreationRequest(
    @JsonProperty("title")
    var title: String
)

class TodoUpdateRequest(
    var title: String,
    var completed: Boolean,
    var order: Int
)

class TodoPartialUpdateRequest(
    var title: String,
    var completed: Boolean,
    var order: Int
)