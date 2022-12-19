package com.example.todokotlinspringddd.server;

import jakarta.persistence.Entity;
import jakarta.persistence.Id

@Entity
class TodoJpa(
        @Id var id:String?=null,
        var title:String,
        var completed:Boolean,
        var rank:Int
)