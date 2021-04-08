package dev.anyjava.actorzip.feed.domain

import java.time.LocalDateTime


fun Feed.Companion.anyObjectForTest() : Feed {
    return Feed("title", "http://a.com", LocalDateTime.now())
}