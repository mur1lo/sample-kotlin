package io.github.murilo.javalin.sample.app.web.entities

import io.github.murilo.javalin.sample.domain.entity.User

class UserRequest(
    val id: Long,
    val uid: String,
    val name: String,
    val email: String,
    val phone: String
) {
    fun toModel(): User =
        User(
            id = this.id,
            name = this.name,
            email = this.email,
            phone = this.phone
        )
}