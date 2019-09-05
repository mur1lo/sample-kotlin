package io.github.murilo.javalin.sample.domain.service

interface ClientService<Client> {
    fun save(cliente: Client): Client
    fun findAll(): List<Client>
    fun update(client: Client): Client
}