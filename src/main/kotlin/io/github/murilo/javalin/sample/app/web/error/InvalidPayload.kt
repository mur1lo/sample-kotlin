package io.github.murilo.javalin.sample.app.web.error

class InvalidPaiload(
    val type: String,
    override val message: String
) : Exception(message)
