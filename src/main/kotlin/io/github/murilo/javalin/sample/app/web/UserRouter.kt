package io.github.murilo.javalin.sample.app.web

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

class UserRouter(private val controller: UserController) {
    fun register(app: Javalin) {
        app.routes {
            path("users") {
                get(controller.getAll())
                post(controller.create())
                path(":id") {
                    get(controller.getOne())
                    put(controller.update())
                    delete(controller.delete())
                }
                path("teste") {
                    post { ctx -> ctx.json(controller.registerUser(ctx)) }
                }
            }
        }
    }
}
