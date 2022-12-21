package com.example.todokotlinspringddd.user

import com.example.todokotlinspringddd.domain.TodoCreationRequestDomain
import com.example.todokotlinspringddd.domain.TodoPartialUpdateRequestDomain
import com.example.todokotlinspringddd.domain.TodoFullUpdateRequestDomain
import org.springframework.stereotype.Service

@Service
class RequestMapper {
    fun creationRequestApiToDomain(request : TodoCreationRequestApi) : TodoCreationRequestDomain {
        return TodoCreationRequestDomain(request.title)
    }

    fun partialUpdateRequestApiToDomain(request: TodoPartialUpdateRequestApi) : TodoPartialUpdateRequestDomain {
        return TodoPartialUpdateRequestDomain(request.title, request.completed, request.rank)
    }

    fun fullUpdateRequestApiToDomain(request: TodoFullUpdateRequestApi) : TodoFullUpdateRequestDomain {
        return TodoFullUpdateRequestDomain(request.title, request.completed, request.rank)
    }
}