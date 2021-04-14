package dev.anyjava.actorzip.adapter.event

import dev.anyjava.actorzip.feed.application.MediaFeedApplicationService
import dev.anyjava.actorzip.feed.domain.FeedsEvent
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

private val logger = KotlinLogging.logger {}

@Component
class DomainEventConsumer(val mediaFeedApplicationService: MediaFeedApplicationService) {

    @TransactionalEventListener(
        phase = TransactionPhase.AFTER_COMMIT,
        classes = [FeedsEvent::class],
        fallbackExecution = true
    )
    fun handleEvent(feedsEvent: FeedsEvent) {
        logger.info("consuming, feed= ${feedsEvent}")
        feedsEvent.feeds.asSequence().forEach { mediaFeedApplicationService.run(it) }
    }
}