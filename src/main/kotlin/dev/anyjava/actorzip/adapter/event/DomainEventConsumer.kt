package dev.anyjava.actorzip.adapter.event

import dev.anyjava.actorzip.feed.application.MediaFeedApplicationService
import dev.anyjava.actorzip.feed.domain.Feed
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class DomainEventConsumer(val mediaFeedApplicationService: MediaFeedApplicationService) {

    fun handleFeedEvent(feed: Feed) {
        try {
            mediaFeedApplicationService.scrap(feed)
        } catch (e: RuntimeException) {
            logger.error("failed handle Feed Event, message=${e.message}", e)
        }
    }
}