package io.github.murilo.javalin.sample.resources.entity

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table


enum class TypePhone(type: String) {
    COMERCIAL("C"), RESIDENCIAL("R")
}

object PhoneTable : Table() {
    val clienteId = long("cliente_id").references(
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE,
        ref = ClientTable.id
    )
    // val clienteId = long("cliente_id").references(ClientTable.id)
    val type = varchar("type", 1)
    val number = varchar("number", 50)
//    val type2 = enumValues<TypePhone>()
//    val enu= enumerationByName("type2", 15, TypePhone::class.java.kotlin)
}
