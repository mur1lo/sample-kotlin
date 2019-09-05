package io.github.murilo.javalin.sample.app.web.error

data class HttpError(
    val type: String,
    val message: String?
)
