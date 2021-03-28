package dev.anyjava.actorzip.feed.service

import dev.anyjava.actorzip.feed.domain.Feed
import dev.anyjava.actorzip.feed.domain.SiteType
import java.time.LocalDateTime

interface FeedScraper {
    fun accept(siteType: SiteType): Boolean
    fun scrap(lastDateTime: LocalDateTime): List<Feed>
}