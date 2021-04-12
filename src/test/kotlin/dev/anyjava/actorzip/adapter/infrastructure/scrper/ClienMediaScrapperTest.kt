package dev.anyjava.actorzip.adapter.infrastructure.scrper

import dev.anyjava.actorzip.feed.domain.Feed
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class ClienMediaScrapperTest(val clienMediaImageScrapper: ClienMediaImageScrapper) : FunSpec({

    test("test check exists images") {
        // given
        val feed = Feed("방금 올라온 안될과학 보다가 빵ㅋㅋㅋㅋㅋ", "/service/board/park/16053128?combine=true&q=%ED%95%9C%EC%98%88%EB%A6%AC&p=0&sort=recency&boardCd=&isBoard=false", LocalDateTime.now())

        // when
        val accept = clienMediaImageScrapper.accept(feed)

        // then
        accept shouldBe true
    }

    test("test check do not exists content") {
        // given
        val feed = Feed("방금 올라온 안될과학 보다가 빵ㅋㅋㅋㅋㅋ", "/service/board/park/16059569?od=T31&po=0&category=0&groupCd=", LocalDateTime.now())

        // when
        val accept = clienMediaImageScrapper.accept(feed)

        // then
        accept shouldBe false
    }

    test("test parsing single images") {
        // given
        val feed = Feed("방금 올라온 안될과학 보다가 빵ㅋㅋㅋㅋㅋ", "/service/board/park/16053128?combine=true&q=%ED%95%9C%EC%98%88%EB%A6%AC&p=0&sort=recency&boardCd=&isBoard=false", LocalDateTime.now())

        // when
        val mediaFeed = clienMediaImageScrapper.scrap(feed)

        // then
        mediaFeed.size shouldBe 1
        mediaFeed.get(0).url shouldBe "https://cdn.clien.net/web/api/file/F01/11402689/41eaa0514aa1a6.jpg?w=780&h=30000&gif=true"
    }

    test("test parsing multi images") {
        // given
        val feed = Feed("test3", "/service/board/park/16059564?od=T31&po=0&category=0&groupCd=", LocalDateTime.now())

        // when
        val mediaFeed = clienMediaImageScrapper.scrap(feed)

        // then
        mediaFeed.size shouldBe 8
    }


}) {
    constructor() : this(ClienMediaImageScrapper())
}
