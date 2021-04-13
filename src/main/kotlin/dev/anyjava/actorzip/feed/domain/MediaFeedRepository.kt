package dev.anyjava.actorzip.feed.domain

import org.springframework.stereotype.Component

interface MediaFeedRepository {
    fun saveAll(mediaFeeds: List<MediaFeed>)
}

@Component
class MediaFeedRepositoryImpl : MediaFeedRepository {
    override fun saveAll(mediaFeeds: List<MediaFeed>) {
        TODO("Not yet implemented")
    }
}