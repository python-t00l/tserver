package com.titan.server.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String? = null

    constructor() {}

    constructor(name: String) {
        this.name = name
    }

    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}'
    }
}
