package io.github.murilo.javalin.sample.domain.entity

data class Client(val id: Long?, val name: String, var phones: List<Phone>?)