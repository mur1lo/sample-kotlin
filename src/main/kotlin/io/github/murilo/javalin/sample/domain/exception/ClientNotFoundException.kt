package io.github.murilo.javalin.sample.domain.exception

class ClientNotFoundException(id: Long) : RuntimeException("Client $id not found")