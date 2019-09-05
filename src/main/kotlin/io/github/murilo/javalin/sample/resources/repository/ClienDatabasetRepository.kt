package io.github.murilo.javalin.sample.resources.repository

import io.github.murilo.javalin.sample.domain.entity.Client
import io.github.murilo.javalin.sample.domain.entity.Phone
import io.github.murilo.javalin.sample.domain.exception.ClientNotFoundException
import io.github.murilo.javalin.sample.domain.repository.ClientRepository
import io.github.murilo.javalin.sample.resources.entity.ClientTable
import io.github.murilo.javalin.sample.resources.entity.PhoneTable
import io.github.murilo.javalin.sample.resources.extension.toClientDomain
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import javax.sql.DataSource

class ClienDatabasetRepository(private val dataSource: DataSource) : ClientRepository {

    private val logger = LoggerFactory.getLogger(ClienDatabasetRepository::class.java)

    init {
        logger.info("INIT Classe repository")
        transaction(Database.connect(dataSource)) {
            SchemaUtils.create(ClientTable, PhoneTable)
        }
    }

    private fun <T> transaction(body: () -> T) = transaction(Database.connect(dataSource)) { body() }

    override fun save(cliente: Client): Client = transaction {

        val clienteId = ClientTable.insert {
            it[name] = cliente.name
        } get ClientTable.id


        cliente.phones?.forEach { phone: Phone ->
            PhoneTable.insert {
                it[type] = phone.type
                it[number] = phone.number
                it[PhoneTable.clienteId] = clienteId!!
            }
            phone.clienteId = clienteId
        }
        cliente.copy(id = clienteId)
    }

    override fun update(id: Long, client: Client) = transaction {
        ClientTable.update({ ClientTable.id eq id }) {
            it[name] = client.name
        }.let {
            PhoneTable.deleteWhere { PhoneTable.clienteId eq id }
            client.phones?.forEach { p ->
                PhoneTable.insert {
                    it[type] = p.type
                    it[number] = p.number
                    it[clienteId] = id
                }
            }
        }.let { findOne(id) }
    }

    override fun findAll(): List<Client> =
        transaction { ClientTable.selectAll().map { it.toClientDomain() }.toList() }

    override fun findOne(id: Long): Client =
        ClientTable.select { ClientTable.id eq (id) }.map { it.toClientDomain() }.firstOrNull()
            ?: throw ClientNotFoundException(id)
}