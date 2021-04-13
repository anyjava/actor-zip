package dev.anyjava.actorzip.feed.application

import dev.anyjava.actorzip.feed.domain.Feed
import dev.anyjava.actorzip.feed.domain.MediaFeedRepository
import dev.anyjava.actorzip.feed.service.MediaFeedFinder
import org.springframework.stereotype.Service

@Service
class MediaFeedApplicationService(
    val mediaFeedFinders: List<MediaFeedFinder> = listOf(),
    val mediaFeedRepository: MediaFeedRepository,
) {

    fun run(feed: Feed) {
        mediaFeedFinders.asSequence()
            .filter { it.acceptSite(feed) }
            .filter { it.accept(feed) }
            .map { it.scrap(feed) }
            .forEach { mediaFeedRepository.saveAll(it) }
    }
}
