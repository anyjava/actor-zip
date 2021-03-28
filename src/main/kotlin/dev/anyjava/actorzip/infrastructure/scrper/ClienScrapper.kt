package dev.anyjava.actorzip.infrastructure.scrper

import dev.anyjava.actorzip.feed.domain.Feed
import dev.anyjava.actorzip.feed.service.FeedScraper
import org.jsoup.Jsoup
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ClienScrapper : FeedScraper {

    override fun scrap(lastSequence: Long): List<Feed> {
        val document = Jsoup.connect("https://www.clien.net/service/search?q=%ED%95%9C%EC%98%88%EB%A6%AC").get()
        return document.select(".subject_fixed").asSequence()
            .map { Feed(it.attr("title"), it.attr("href"), LocalDateTime.now())}
            .toList()
    }
}