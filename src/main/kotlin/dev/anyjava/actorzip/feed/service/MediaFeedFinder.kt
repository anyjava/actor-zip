package dev.anyjava.actorzip.feed.service

import dev.anyjava.actorzip.feed.domain.Feed
import dev.anyjava.actorzip.feed.domain.MediaFeed

interface MediaFeedFinder {
    fun acceptSite(feed: Feed): Boolean
    fun accept(feed: Feed): Boolean
    fun scrap(feed: Feed): List<MediaFeed>
}