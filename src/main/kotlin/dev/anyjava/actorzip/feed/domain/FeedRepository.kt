package dev.anyjava.actorzip.feed.domain

import dev.anyjava.actorzip.feed.service.FeedScraper
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.stereotype.Repository

interface FeedRepository {
    fun findAll(pageable: Pageable): Slice<Feed>
}

/**
 * 잠시 적용해둔 respository
 */
@Repository
class FeedClienRepository(private val feedScraper: FeedScraper) : FeedRepository {
    override fun findAll(pageable: Pageable): Slice<Feed> {
        val list =feedScraper.scrap(1L)
            .drop(pageable.pageNumber * pageable.pageSize)
            .take(pageable.pageSize)
            .toList()

        return SliceImpl(list, pageable, true)
    }
}