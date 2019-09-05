package io.github.murilo.javalin.sample.domain.repository

import io.github.murilo.javalin.sample.domain.entity.User


interface UserRepository {

    fun getAll(): List<User>

    fun getOne(id: Long): User

    fun create(user: User): User

    fun update(id: Long, user: User): User

    fun delete(id: Long)

}