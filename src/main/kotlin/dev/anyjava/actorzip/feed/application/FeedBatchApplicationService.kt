package dev.anyjava.actorzip.feed.application

import dev.anyjava.actorzip.feed.domain.SiteType
import dev.anyjava.actorzip.feed.service.FeedScrapingService
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class FeedBatchApplicationService(private val feedScrapingService: FeedScrapingService) {

    @Scheduled(fixedDelay = 1000 * 60 * 10)
    fun runScrap() {
        logger.info("START scrapping")
        SiteType.values()
            .forEach { feedScrapingService.scrap(it) }
        logger.info("END scrapping")
    }
}