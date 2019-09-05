package io.github.murilo.javalin.sample.app.web.entities

import io.github.murilo.javalin.sample.domain.entity.User

class UserResponse(
    val id: Long?,
    val name: String,
    val email: String,
    val phone: String?
) {
    companion object {
        fun toResponse(user: User): UserResponse =
            UserResponse(
                id = user.id,
                name = user.name,
                email = user.email,
                phone = user.phone
            )
    }
}