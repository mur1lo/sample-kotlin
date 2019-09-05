package io.github.murilo.javalin.sample.resources.entity

import org.jetbrains.exposed.sql.Table

object ClientTable : Table() {
    val id = long("id").autoIncrement().primaryKey()
    val name = varchar("name", 50).uniqueIndex()
}