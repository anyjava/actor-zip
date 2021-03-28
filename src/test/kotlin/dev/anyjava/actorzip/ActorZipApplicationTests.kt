package dev.anyjava.actorzip

import io.kotest.core.spec.style.FunSpec
import io.kotest.spring.SpringListener
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [ActorZipApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActorZipApplicationTests() : FunSpec({
    test("context Loads") {

    }
}) {
    override fun listeners() = listOf(SpringListener)
}