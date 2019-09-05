package io.github.murilo.javalin.sample.app.web

import io.github.murilo.javalin.sample.domain.entity.Client
import io.github.murilo.javalin.sample.domain.service.ClientService
import io.javalin.Handler
import org.slf4j.LoggerFactory

class ClientController(private val service: ClientService<Client>) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun create() = Handler { it.json(service.save(it.bodyAsClass(Client::class.java))) }
    fun findAll() = Handler { it.json(service.findAll()) }
    fun update() = Handler { it.json(service.update(it.bodyAsClass(Client::class.java))) }
}