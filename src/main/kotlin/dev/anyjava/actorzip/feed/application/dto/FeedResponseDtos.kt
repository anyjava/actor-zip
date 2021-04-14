package dev.anyjava.actorzip.feed.application.dto

import dev.anyjava.actorzip.feed.domain.MediaFeed
import dev.anyjava.actorzip.feed.domain.MediaType
import dev.anyjava.actorzip.feed.domain.SiteType
import java.time.LocalDateTime

data class FeedsResponse(
    val title: String,
    val siteType: SiteType,
    val url: String,
    val registrationDateTime: LocalDateTime
)

data class MediaFeedResponse(
    val siteType: SiteType,
    val mediaType: MediaType,
    val imageUrl: String,
    val originUrl: String,
    val thumbnail: String,
) {
    constructor(mediaFeed: MediaFeed) : this(
        mediaFeed.feed.siteType,
        mediaFeed.type,
        mediaFeed.url,
        mediaFeed.feed.getContentUrl(),
        mediaFeed.thumbnail
    )
}

