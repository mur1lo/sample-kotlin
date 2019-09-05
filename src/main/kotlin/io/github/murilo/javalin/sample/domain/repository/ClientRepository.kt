package io.github.murilo.javalin.sample.domain.repository

import io.github.murilo.javalin.sample.domain.entity.Client

interface ClientRepository {
    fun save(cliente: Client): Client
    fun update(id: Long, client: Client): Client
    fun findAll(): List<Client>
    fun findOne(id: Long): Client
}