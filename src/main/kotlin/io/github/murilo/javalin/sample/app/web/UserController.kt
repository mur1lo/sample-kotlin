package io.github.murilo.javalin.sample.app.web

import io.github.murilo.javalin.sample.app.web.entities.UserRequest
import io.github.murilo.javalin.sample.app.web.entities.UserResponse
import io.github.murilo.javalin.sample.app.web.error.InvalidPaiload
import io.github.murilo.javalin.sample.domain.entity.User
import io.github.murilo.javalin.sample.domain.service.UserService
import io.github.murilo.javalin.sample.resources.extension.paramAsLong
import io.javalin.BadRequestResponse
import io.javalin.Context
import io.javalin.Handler
import org.eclipse.jetty.http.HttpStatus
import org.slf4j.LoggerFactory
import java.util.*

class UserController(
    private val userService: UserService<User>
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun getAll() = Handler { it.json(userService.getAll()) }

    fun getOne() = Handler { it.json(userService.getOne(it.paramAsLong("id"))) }

    fun create() = Handler { it.json(userService.create(it.bodyAsClass(User::class.java))) }

    fun update() = Handler {
        val id = it.paramAsLong("id")
        val user = it.bodyAsClass(User::class.java)
        it.json(userService.update(id, user))
    }

    fun delete() = Handler {
        userService.delete(it.paramAsLong("id"))
        it.status(204)
    }

    fun registerUser(ctx: Context): UserResponse = try {
        ctx.bodyAsClass(UserRequest::class.java).let {
            logger.info("Save user with name ${it.name}")
            UserResponse.toResponse(it.toModel())
        }.also { ctx.status(HttpStatus.CREATED_201) }
    } catch (ex: BadRequestResponse) {
        logger.error(ex.toString())
        throw InvalidPaiload(
            type = "Payload invalid",
            message = ex.message.toString()
        )
    }
}