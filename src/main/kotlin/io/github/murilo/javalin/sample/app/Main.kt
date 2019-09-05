package io.github.murilo.javalin.sample.app

import io.github.murilo.javalin.sample.app.config.App
import io.github.murilo.javalin.sample.app.web.error.HandlerError
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.ISODateTimeFormat
import java.lang.Exception
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun main() {
    App().setup().start()



    println(Car.makeCar(40))


    qualquerCoisa { println("bbbbbb") }

    //Teste().testeFun()
}

class Teste {
    fun testeFun() = println(teste)

    companion object {
        val teste = "A"
    }
}


inline fun qualquerCoisa(funcao: () -> Unit) {
    println("aaaaaaaaaaaa")
    funcao()
    println("cccccccccccccccccc")
}

data class Car(val horsepowers: Int) {
    companion object Factory {
        val cars = mutableListOf<Car>()
        fun makeCar(horsepowers: Int): Car {
            val car = Car(horsepowers)
            cars.add(car)
            return car
        }
    }
}

data class UsuarioDto(val nome: String) {
    companion object {
        fun toEntity(usuarioDto: UsuarioDto) = Usuario(nome = usuarioDto.nome)
    }
}

data class Usuario(val nome: String) {
    companion object {
        fun toDto(usuario: Usuario): UsuarioDto = UsuarioDto(nome = usuario.nome)
    }
}