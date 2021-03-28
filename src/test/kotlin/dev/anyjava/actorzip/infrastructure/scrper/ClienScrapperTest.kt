package dev.anyjava.actorzip.infrastructure.scrper

import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.shouldBe
import mu.KotlinLogging
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {}

class ClienScrapperTest(val clienScrapper: ClienScrapper) : FunSpec({

    test("desc") {
        val feeds = clienScrapper.scrap(LocalDateTime.MIN)

        feeds.size shouldBe 20
        logger.info("feeds = $feeds")
    }

}) {
    constructor() : this(ClienScrapper())

    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
    }
}