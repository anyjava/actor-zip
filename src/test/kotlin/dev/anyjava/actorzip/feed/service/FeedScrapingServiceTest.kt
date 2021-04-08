package dev.anyjava.actorzip.feed.service

import com.nhaarman.mockito_kotlin.*
import dev.anyjava.actorzip.feed.domain.Feed
import dev.anyjava.actorzip.feed.domain.FeedRepository
import dev.anyjava.actorzip.feed.domain.SiteType
import dev.anyjava.actorzip.feed.domain.anyObjectForTest
import io.kotest.core.spec.style.AnnotationSpec
import org.springframework.context.ApplicationEventPublisher


class FeedScrapingServiceTest : AnnotationSpec() {

    var feedRepository: FeedRepository = mock {
        on { findTop1BySiteTypeOrderByRegistrationDateTimeDesc(SiteType.CLIEN) } doReturn null
    }
    var scrappers: List<FeedScraper> = listOf(mock {
        on { accept(SiteType.CLIEN) } doReturn true
        on { scrap(any()) } doReturn listOf(Feed.anyObjectForTest())
    })
    var applicationEventPublisher: ApplicationEventPublisher = mock {}

    var feedScrapingService: FeedScrapingService =
        FeedScrapingService(feedRepository, scrappers, applicationEventPublisher)

    @Test
    fun testPublishDomainEvent() {
        // given
        feedRepository.findTop1BySiteTypeOrderByRegistrationDateTimeDesc(SiteType.CLIEN)

        // when
        feedScrapingService.scrap(SiteType.CLIEN)

        // then
        verify(applicationEventPublisher).publishEvent(any<List<Feed>>())
    }
}
