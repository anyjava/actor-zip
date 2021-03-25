package dev.anyjava.actorzip.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ActorZipApplication

fun main(args: Array<String>) {
    runApplication<ActorZipApplication>(*args)
}
