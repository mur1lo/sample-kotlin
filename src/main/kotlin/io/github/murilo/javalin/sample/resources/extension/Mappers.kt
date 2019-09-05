package io.github.murilo.javalin.sample.resources.extension

import io.github.murilo.javalin.sample.domain.entity.Client
import io.github.murilo.javalin.sample.domain.entity.Phone
import io.github.murilo.javalin.sample.domain.entity.User
import io.github.murilo.javalin.sample.resources.entity.ClientTable
import io.github.murilo.javalin.sample.resources.entity.PhoneTable
import io.github.murilo.javalin.sample.resources.entity.UserTable
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select

fun ResultRow.toUserDomain() = User(
    id = this[UserTable.id],
    name = this[UserTable.name],
    email = this[UserTable.email],
    phone = this[UserTable.phone]
)

fun ResultRow.toClientDomain() = Client(
    id = get(ClientTable.id),
    name = get(ClientTable.name),
    phones = PhoneTable.select { PhoneTable.clienteId eq get(ClientTable.id) }.map { it.toPhones() }.toList()
    /*phones = ClientTable.join(
        PhoneTable,
        JoinType.LEFT,
        ClientTable.id,
        PhoneTable.clienteId
    ).select { PhoneTable.clienteId eq get(ClientTable.id) }.map { phone -> phone.toPhones() }    //.selectAll().map { phone -> phone.toPhones() }*/
)

fun ResultRow.toPhones() = Phone(
    clienteId = get(PhoneTable.clienteId),
    number = get(PhoneTable.number),
    type = get(PhoneTable.type)
)