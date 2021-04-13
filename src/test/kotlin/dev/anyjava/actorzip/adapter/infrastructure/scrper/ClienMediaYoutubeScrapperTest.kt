package dev.anyjava.actorzip.adapter.infrastructure.scrper

import dev.anyjava.actorzip.feed.domain.Feed
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class ClienMediaYoutubeScrapperTest(val clienMediaYoutubeScrapper: ClienMediaYoutubeScrapper) : FunSpec({


    test("test accept exits youtube link") {
        // given
        val feed = Feed("test3", "/service/board/park/16059564?od=T31&po=0&category=0&groupCd=", LocalDateTime.now())

        // when
        val result = clienMediaYoutubeScrapper.accept(feed)

        // then
        result shouldBe true
    }

    test("test accept false, not exits youtube link") {
        // given
        val feed = Feed("test3", "/service/board/park/16062401?combine=true&q=gif&p=0&sort=recency&boardCd=&isBoard=false", LocalDateTime.now())

        // when
        val result = clienMediaYoutubeScrapper.accept(feed)

        // then
        result shouldBe false
    }

    test("test scrapping youtube video") {
        // given
        val feed = Feed("test3", "/service/board/park/16059564?od=T31&po=0&category=0&groupCd=", LocalDateTime.now())

        // when
        val mediaFeeds = clienMediaYoutubeScrapper.scrap(feed)

        // then
        mediaFeeds.size shouldBe 3
        mediaFeeds[0].url shouldBe "https://www.youtube.com/embed/3Z0N2jTS3_k"

    }

}) {
    constructor() : this(ClienMediaYoutubeScrapper())
}
