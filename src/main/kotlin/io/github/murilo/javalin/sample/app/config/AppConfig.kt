package io.github.murilo.javalin.sample.app.config

import io.github.murilo.javalin.sample.app.web.ClienteRouter
import io.github.murilo.javalin.sample.app.web.UserRouter
import io.github.murilo.javalin.sample.app.web.error.HandlerError
import io.github.murilo.javalin.sample.domain.exception.UserNotFoundException
import io.javalin.Javalin
import io.javalin.JavalinEvent.SERVER_STOPPING
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import java.lang.Exception

class App : KoinComponent {

    private val userRouter: UserRouter by inject()
    private val clienteRouter: ClienteRouter by inject()

    fun setup(): Javalin {
        StandAloneContext.startKoin(appModules)
        return Javalin.create()
            .also { app ->
                app.enableCorsForAllOrigins()
                app.event(SERVER_STOPPING) { StandAloneContext.stopKoin() }
                userRouter.register(app)
                clienteRouter.register(app)
                app.exception(UserNotFoundException::class.java) { exception, ctx ->
                    ctx.status(404)
                    exception.message?.let { ctx.result(it) }
                }
                app.exception(Exception::class.java, HandlerError::handlerErrorException)
                app.port(7000)
            }
    }

}
