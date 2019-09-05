package io.github.murilo.javalin.sample.app.web.error

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import io.javalin.Context
import org.eclipse.jetty.http.HttpStatus

object HandlerError {
    fun handlerErrorException(e: Exception, ctx: Context) {
        val (httpError, httpStatus) = when (e) {
            is InvalidPaiload -> {
                HttpError(e.type, e.message) to HttpStatus.UNPROCESSABLE_ENTITY_422
            }
            is MissingKotlinParameterException -> {
                HttpError("MissingParameterException", e.message.toString()) to HttpStatus.UNPROCESSABLE_ENTITY_422
            }
            else -> {
                HttpError("Unknown error", "error not identified") to HttpStatus.INTERNAL_SERVER_ERROR_500
            }
        }
        ctx.status(httpStatus)
        ctx.json(httpError)
    }
}