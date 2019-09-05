package io.github.murilo.javalin.sample.app.config

import io.github.murilo.javalin.sample.app.web.ClientController
import io.github.murilo.javalin.sample.app.web.ClienteRouter
import io.github.murilo.javalin.sample.app.web.UserController
import io.github.murilo.javalin.sample.app.web.UserRouter
import io.github.murilo.javalin.sample.domain.entity.Client
import io.github.murilo.javalin.sample.domain.entity.User
import io.github.murilo.javalin.sample.domain.repository.ClientRepository
import io.github.murilo.javalin.sample.domain.repository.UserRepository
import io.github.murilo.javalin.sample.domain.service.ClientService
import io.github.murilo.javalin.sample.domain.service.UserService
import io.github.murilo.javalin.sample.domain.service.impl.ClientServiceImpl
import io.github.murilo.javalin.sample.domain.service.impl.UserServiceImpl
import io.github.murilo.javalin.sample.resources.repository.ClienDatabasetRepository
import io.github.murilo.javalin.sample.resources.repository.UserDatabaseRepository
import org.koin.dsl.module.module

val configModule = module {
    single { App() }
    single {
        DatabaseConfig(
            jdbcUrl,
            jdbcDriver,
            dbUsername,
            dbPassword
        ).dataSource
    }
    single { UserRouter(get()) }
    single { ClienteRouter(get()) }
}

val userModule = module {
    single { UserDatabaseRepository(get()) as UserRepository }
    single { UserController(get()) }
    single<UserService<User>> {
        UserServiceImpl(
            get()
        )
    }
}

val clientModules = module {
    single { ClienDatabasetRepository(get()) as ClientRepository }
    single { ClientController(get()) }
    single<ClientService<Client>> { ClientServiceImpl(get()) }

}

val appModules = listOf(
    configModule,
    userModule,
    clientModules
)