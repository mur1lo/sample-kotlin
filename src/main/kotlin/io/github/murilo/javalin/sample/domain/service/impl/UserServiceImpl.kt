package io.github.murilo.javalin.sample.domain.service.impl

import io.github.murilo.javalin.sample.domain.entity.User
import io.github.murilo.javalin.sample.domain.repository.UserRepository
import io.github.murilo.javalin.sample.domain.service.UserService
import org.slf4j.LoggerFactory

class UserServiceImpl(private val repository: UserRepository) :
    UserService<User> {

    private val logger = LoggerFactory.getLogger(UserServiceImpl::class.java)

    override fun getOne(id: Long): User = try {
        this.repository.getOne(id).also {
            logger.info("User getOne sucess")
        }
    } catch (ex: Exception) {
        logger.error(ex.toString())
        throw java.lang.Exception("Error update user")
    }

    override fun update(id: Long, user: User): User = try {
        this.repository.update(id, user).also {
            logger.info("User update with sucess: $it")
        }
    } catch (ex: Exception) {
        logger.error(ex.toString())
        throw java.lang.Exception("Error update user")
    }

    override fun delete(id: Long) = try {
        this.repository.delete(id).also {
            logger.info("User delete with sucess: $id")
        }
    } catch (ex: Exception) {
        logger.error(ex.toString())
        throw java.lang.Exception("Error delete user: $id")
    }

    override fun create(entity: User): User = try {
        this.repository.create(entity).also {
            logger.info("User create with success: $it")
        }
    } catch (ex: Exception) {
        logger.error(ex.toString())
        throw java.lang.Exception("Error create user")
    }

    override fun getAll(): List<User> = try {
        this.repository.getAll().also {
            logger.info("GetAll user with success!")
        }
    } catch (ex: Exception) {
        logger.error(ex.toString())
        throw java.lang.Exception("Error getAll user")
    }
}