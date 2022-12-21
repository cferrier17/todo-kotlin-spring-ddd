package com.example.todokotlinspringddd.user

import com.fasterxml.jackson.annotation.JsonProperty


class TodoCreationRequestApi(
    @JsonProperty("title")
    var title: String
)

class TodoFullUpdateRequestApi(
    @JsonProperty("title")
    var title: String,
    @JsonProperty("completed")
    var completed: Boolean,
    @JsonProperty("rank")
    var rank: Int
)

class TodoPartialUpdateRequestApi(
    @JsonProperty("title")
    var title: String?,
    @JsonProperty("completed")
    var completed: Boolean?,
    @JsonProperty("rank")
    var rank: Int?
)