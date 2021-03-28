package dev.anyjava.actorzip.feed.service

import dev.anyjava.actorzip.feed.domain.Feed

interface FeedScraper {
    fun scrap(lastSequence: Long): List<Feed>
}