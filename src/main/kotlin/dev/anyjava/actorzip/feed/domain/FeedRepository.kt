package dev.anyjava.actorzip.feed.domain

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import javax.annotation.PostConstruct

interface FeedRepository {
    fun findAll(pageable: Pageable): Slice<Feed>
}

@Repository
class FeedInMemoryRepository : FeedRepository {

    lateinit var allFeeds: List<Feed>

    override fun findAll(pageable: Pageable): Slice<Feed> {
        val list = this.allFeeds
            .drop(pageable.pageNumber * pageable.pageSize)
            .take(pageable.pageSize)
            .toList()

        return SliceImpl(list, pageable, true)
    }

    @PostConstruct
    fun setUp() {
        val naturalNumbers = generateSequence(0) { it + 1 }
        val numbersTo100 = naturalNumbers.takeWhile { it < 50 }

        allFeeds = numbersTo100.asSequence()
            .map { this.getOne(it) }
            .toList()
    }

    private fun getOne(idx: Int) : Feed {
        return Feed("테스트 게시글 $idx", "http://www.clien.net/", LocalDateTime.now())
    }
}