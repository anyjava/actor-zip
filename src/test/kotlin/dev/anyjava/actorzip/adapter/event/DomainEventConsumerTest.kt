package dev.anyjava.actorzip.adapter.event

import dev.anyjava.actorzip.feed.application.MediaFeedApplicationService
import dev.anyjava.actorzip.feed.domain.Feed
import io.kotest.core.spec.style.AnnotationSpec
import java.time.LocalDateTime

class DomainEventConsumerTest : AnnotationSpec() {

    var mediaFeedApplicationService: MediaFeedApplicationService = mock {
    }

    val domainEventConsumer = DomainEventConsumer(mediaFeedApplicationService)

    @Test
    fun testWhenConsumerEventThenInvokeApplicationService() {
        // given
        val feed = Feed("title", "http", LocalDateTime.now())

        // when
        domainEventConsumer.handleFeedEvent(feed)

        // then
        verify(mediaFeedApplicationService, times(1)).scrap(any())
    }
}
