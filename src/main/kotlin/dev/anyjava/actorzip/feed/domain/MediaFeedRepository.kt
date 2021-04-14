package dev.anyjava.actorzip.feed.domain

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.SliceImpl
import org.springframework.stereotype.Component

interface MediaFeedRepository {
    fun saveAll(mediaFeeds: List<MediaFeed>)
    fun findAll(pageable: Pageable): SliceImpl<MediaFeed>
    fun findAll(): List<MediaFeed>
}

@Component
class MediaFeedInMemoryRepository(val mem: MutableList<MediaFeed> = mutableListOf()) : MediaFeedRepository {
    override fun saveAll(mediaFeeds: List<MediaFeed>) {
        mem.addAll(mediaFeeds)
    }

    override fun findAll(pageable: Pageable): SliceImpl<MediaFeed> {
        val list = mem.asSequence()
            .sortedByDescending { it.registrationDateTime }
            .drop(pageable.pageNumber * pageable.pageSize)
            .take(pageable.pageSize)
            .toList()
        return SliceImpl(list, pageable, list.size == pageable.pageSize)
    }

    override fun findAll(): List<MediaFeed> {
        return mem
    }
}