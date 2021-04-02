package dev.anyjava.actorzip.adapter.infrastructure.scrper

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DcScrapperTest(val dcScrapper: DcScrapper) : FunSpec({
    test("scrapping dc") {
        val list = dcScrapper.scrap(LocalDateTime.MIN)

        list.size shouldBeGreaterThan 1
    }

    test("now 던지면 조회되는 feed 가 없음") {
        val list = dcScrapper.scrap(LocalDateTime.now())

        list.size shouldBe 0
    }

    test("datetime formatter") {
        val title = "2021-03-26 19:28:06"

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dt = LocalDateTime.parse(title, formatter)

        dt shouldBe LocalDateTime.of(2021, 3, 26, 19, 28, 6)
    }
}) {
    constructor() : this(DcScrapper())
}
