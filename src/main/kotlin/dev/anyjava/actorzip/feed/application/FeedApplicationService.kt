package dev.anyjava.actorzip.feed.application

import dev.anyjava.actorzip.adapter.presentation.controller.FeedsResponse
import dev.anyjava.actorzip.feed.domain.Feed
import dev.anyjava.actorzip.feed.domain.FeedRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class FeedApplicationService {

    var feedRepository: FeedRepository
    var p: PageableHandlerMethodArgumentResolverCustomizer

    constructor(feedRepository: FeedRepository, p: PageableHandlerMethodArgumentResolverCustomizer) {
        this.feedRepository = feedRepository
        this.p = p
    }


    fun getFeeds(pageable: Pageable): Slice<FeedsResponse> {
        print("=========== $p")
        val content = feedRepository.findAll(pageable).asSequence()
            .map { convert(it) }
            .toList()

        return SliceImpl(content)
    }

    private fun convert(it: Feed): FeedsResponse {
        return FeedsResponse(it.title, it.url, it.registrationDateTime)
    }
}