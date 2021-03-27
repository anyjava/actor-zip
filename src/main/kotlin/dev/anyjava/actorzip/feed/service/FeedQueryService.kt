package dev.anyjava.actorzip.feed.service

import dev.anyjava.actorzip.feed.domain.Feed
import org.springframework.stereotype.Service

@Service
class FeedQueryService {

    fun getAllBy() : List<Feed> {
        return emptyList()
    }
}