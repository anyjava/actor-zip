package dev.anyjava.actorzip.feed.application

import dev.anyjava.actorzip.feed.application.dto.FeedsResponse
import dev.anyjava.actorzip.feed.domain.Feed
import dev.anyjava.actorzip.feed.domain.FeedRepository
import dev.anyjava.actorzip.feed.domain.SiteType
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service

@Service
class FeedApplicationService(val feedRepository: FeedRepository) {

    fun getFeeds(excludeSiteTypes: Set<SiteType>, pageable: Pageable): Slice<FeedsResponse> {
        return feedRepository.findAll(excludeSiteTypes, pageable)
            .map { convert(it) }
    }

    private fun convert(it: Feed): FeedsResponse {
        return FeedsResponse(it.title, it.siteType, it.getContentUrl(), it.registrationDateTime)
    }
}