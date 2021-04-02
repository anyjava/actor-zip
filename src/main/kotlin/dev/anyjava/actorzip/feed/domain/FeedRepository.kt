package dev.anyjava.actorzip.feed.domain

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.stereotype.Repository

interface FeedRepository {
    fun findAll(pageable: Pageable): Slice<Feed>
    fun findTop1BySiteTypeOrderByRegistrationDateTimeDesc(siteType: SiteType): Feed?
    fun saveAll(feeds: List<Feed>)
}

/**
 * 잠시 적용해둔 repository
 */
@Repository
class FeedClienRepository : FeedRepository {

    private val memory: MutableList<Feed> = mutableListOf()

    override fun findAll(pageable: Pageable): Slice<Feed> {
        val list = memory
            .sortedByDescending { it.registrationDateTime }
            .drop(pageable.pageNumber * pageable.pageSize)
            .take(pageable.pageSize)
            .toList()

        return SliceImpl(list, pageable, list.size == pageable.pageSize)
    }

    override fun findTop1BySiteTypeOrderByRegistrationDateTimeDesc(siteType: SiteType): Feed? {
        return memory.filter { it.siteType == siteType }
            .maxByOrNull { it.registrationDateTime }
    }

    override fun saveAll(feeds: List<Feed>) {
        memory.addAll(feeds)
    }
}