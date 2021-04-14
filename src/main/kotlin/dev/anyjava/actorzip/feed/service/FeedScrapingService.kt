package dev.anyjava.actorzip.feed.service

import dev.anyjava.actorzip.feed.domain.Feed
import dev.anyjava.actorzip.feed.domain.FeedRepository
import dev.anyjava.actorzip.feed.domain.FeedsEvent
import dev.anyjava.actorzip.feed.domain.SiteType
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class FeedScrapingService(
    private val feedRepository: FeedRepository,
    private val scrappers: List<FeedScraper>,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    // add transactional when using database
    fun scrap(siteType: SiteType) {
        val lastDateTime =
            feedRepository.findTop1BySiteTypeOrderByRegistrationDateTimeDesc(siteType)?.registrationDateTime
                ?: LocalDateTime.MIN

        val contents = scrappers.filter { it.accept(siteType) }
            .flatMap { it.scrap(lastDateTime) }
            .toList()

        feedRepository.saveAll(contents)
        this.publishEvent(contents)
    }

    private fun publishEvent(feeds: List<Feed>) {
        applicationEventPublisher.publishEvent(FeedsEvent(feeds))
    }
}