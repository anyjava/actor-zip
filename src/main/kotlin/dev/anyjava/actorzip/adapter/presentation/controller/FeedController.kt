package dev.anyjava.actorzip.adapter.presentation.controller

import dev.anyjava.actorzip.feed.application.FeedApplicationService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {}

@RestController
class FeedController {

    private val feedApplicationService: FeedApplicationService

    @Autowired
    constructor(feedApplicationService: FeedApplicationService) {
        this.feedApplicationService = feedApplicationService
    }

    @GetMapping("/feeds")
    fun getFeeds(pageRequest: Pageable) : Slice<FeedsResponse> {
        logger.info("pageable = $pageRequest")
        return feedApplicationService.getFeeds(pageRequest)
    }

}

data class FeedsResponse(val title: String, val url: String, val registrationDateTime: LocalDateTime)
