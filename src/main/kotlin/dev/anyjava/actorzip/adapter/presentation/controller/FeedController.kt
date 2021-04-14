package dev.anyjava.actorzip.adapter.presentation.controller

import dev.anyjava.actorzip.feed.application.FeedApplicationService
import dev.anyjava.actorzip.feed.application.MediaFeedApplicationService
import dev.anyjava.actorzip.feed.application.dto.FeedsResponse
import dev.anyjava.actorzip.feed.application.dto.MediaFeedResponse
import dev.anyjava.actorzip.feed.domain.SiteType
import io.swagger.annotations.ApiParam
import mu.KotlinLogging
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@RestController
class FeedController(
    val feedApplicationService: FeedApplicationService,
    val mediaFeedApplicationService: MediaFeedApplicationService
) {

    @GetMapping("/feeds")
    fun getFeeds(
        @ApiParam(
            value = "제외대상 site",
            defaultValue = "",
            example = "CLIEN,DC"
        ) @RequestParam(required = false) excludeSiteTypes: Set<SiteType>?,
        pageRequest: Pageable
    ): Slice<FeedsResponse> {
        logger.info("siteTypes = $excludeSiteTypes, pageable = $pageRequest")
        return feedApplicationService.getFeeds(excludeSiteTypes ?: setOf(), pageRequest)
    }

    @GetMapping("/mediaFeeds")
    fun getImageFeeds(pageRequest: Pageable): Slice<MediaFeedResponse> {
        return mediaFeedApplicationService.getMediaFeeds(pageRequest)
    }

}
