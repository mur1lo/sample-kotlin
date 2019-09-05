package io.github.murilo.javalin.sample.domain.service.impl

import io.github.murilo.javalin.sample.domain.entity.Client
import io.github.murilo.javalin.sample.domain.repository.ClientRepository
import io.github.murilo.javalin.sample.domain.service.ClientService
import org.slf4j.LoggerFactory

class ClientServiceImpl(private val repository: ClientRepository) : ClientService<Client> {

    private val logger = LoggerFactory.getLogger(ClientServiceImpl::class.java)

    override fun findAll(): List<Client> = try {
        this.repository.findAll().also {
            logger.info("Client findAll with success: $it")
        }
    } catch (ex: Exception) {
        logger.error(ex.toString())
        throw java.lang.Exception("Error findall cliente")
    }

    override fun save(cliente: Client): Client = try {
        this.repository.save(cliente).also {
            logger.info("Client create with success: $it")
        }
    } catch (ex: Exception) {
        logger.error(ex.toString())
        throw java.lang.Exception("Error create cliente")
    }

    override fun update(client: Client): Client = try {
        this.repository.update(client.id!!, client).also {
            logger.info("Client create with success: $it")
        }
    } catch (ex: Exception) {
        logger.error(ex.toString())
        throw java.lang.Exception("Error create cliente")
    }
}