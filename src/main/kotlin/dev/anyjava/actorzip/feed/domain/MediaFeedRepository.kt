package dev.anyjava.actorzip.feed.domain

import org.springframework.stereotype.Component

interface MediaFeedRepository {
    fun saveAll(mediaFeeds: List<MediaFeed>)
}

@Component
class MediaFeedInMemoryRepository(val mem: MutableList<MediaFeed> = mutableListOf()) : MediaFeedRepository {
    override fun saveAll(mediaFeeds: List<MediaFeed>) {
        mem.addAll(mediaFeeds)
    }
}