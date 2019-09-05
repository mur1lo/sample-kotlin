package io.github.murilo.javalin.sample.domain.service

interface UserService<User> {

    fun getAll(): List<User>

    fun getOne(id: Long): User

    fun create(user: User): User

    fun update(
        id: Long,
        user: User
    ): User

    fun delete(id: Long)

}