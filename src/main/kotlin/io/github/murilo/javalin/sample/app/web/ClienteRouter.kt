package io.github.murilo.javalin.sample.app.web

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

class ClienteRouter(private val clientController: ClientController) {
    fun register(app: Javalin) {
        app.routes {
            path("client") {
                post(clientController.create())
                put(clientController.update())
                get(clientController.findAll())
            }
        }
    }
}