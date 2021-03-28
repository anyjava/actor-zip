package dev.anyjava.actorzip.feed.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith
import java.time.LocalDateTime

class FeedTest : FunSpec({
    test("Feed 생성 테스트 - 기본사이트 type 은 clien 이다.") {
        Feed("title", "/service", LocalDateTime.now()).siteType shouldBe SiteType.CLIEN
    }

    test("getContentUrl") {
        Feed("title", "/service", LocalDateTime.now()).getContentUrl() shouldStartWith "https://clien.net/"
    }
}) {
}