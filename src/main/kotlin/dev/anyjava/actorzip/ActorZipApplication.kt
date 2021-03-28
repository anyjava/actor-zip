package dev.anyjava.actorzip

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class ActorZipApplication

fun main(args: Array<String>) {
    runApplication<ActorZipApplication>(*args)
}
