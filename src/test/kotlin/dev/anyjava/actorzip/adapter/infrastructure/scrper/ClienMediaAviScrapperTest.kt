package dev.anyjava.actorzip.adapter.infrastructure.scrper

import dev.anyjava.actorzip.feed.domain.Feed
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class ClienMediaAviScrapperTest(val clienMediaAviScrapper: ClienMediaAviScrapper) : FunSpec({

    test("accept") {
        // given
        val feed = Feed("test3", "/service/board/park/16062401?od=T31&po=0&category=0&groupCd=", LocalDateTime.now())

        // when
        val result = clienMediaAviScrapper.accept(feed)

        // then
        result shouldBe true
    }

    test("scrap") {
        // given
        val feed = Feed("test3", "/service/board/park/16062401?od=T31&po=0&category=0&groupCd=", LocalDateTime.now())

        // when
        val mediaFeed = clienMediaAviScrapper.scrap(feed)

        // then
        mediaFeed.size shouldBe 1
        mediaFeed[0].url shouldBe "https://cdn.clien.net/web/api/file/F01/11415062/42e8f0fdfb3585.mp4"
        mediaFeed[0].thumbnail shouldBe "https://cdn.clien.net/web/api/file/F01/11415063/42e8f0fdfb3585.gif?w=780&h=30000"
    }
}) {
    constructor() : this(ClienMediaAviScrapper())
}
