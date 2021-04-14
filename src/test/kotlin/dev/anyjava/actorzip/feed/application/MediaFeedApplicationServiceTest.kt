package dev.anyjava.actorzip.feed.application

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.refEq
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import dev.anyjava.actorzip.feed.domain.Feed
import dev.anyjava.actorzip.feed.domain.MediaFeed
import dev.anyjava.actorzip.feed.domain.MediaFeedRepository
import dev.anyjava.actorzip.feed.service.MediaFeedFinder
import io.kotest.core.spec.style.AnnotationSpec
import java.time.LocalDateTime

class MediaFeedApplicationServiceTest : AnnotationSpec() {

    val anyFeed: Feed = Feed("", "", LocalDateTime.now())

    val mediaFeedFinder: MediaFeedFinder = object:MediaFeedFinder {
        override fun acceptSite(feed: Feed): Boolean {
            return true
        }

        override fun accept(feed: Feed): Boolean {
            return true
        }

        override fun scrap(feed: Feed): List<MediaFeed> {
            return listOf(MediaFeed("", anyFeed))
        }
    }

    val mediaFeedRepository: MediaFeedRepository = mock {
    }

    val mediaFeedApplicationService = MediaFeedApplicationService(listOf(mediaFeedFinder), mediaFeedRepository)

    @Test
    fun `run`() {
        // given
        val feed = Feed("test3", "/service/board/park/16062401?od=T31&po=0&category=0&groupCd=", LocalDateTime.now())

        // when
        mediaFeedApplicationService.run(feed)

        // then
        verify(mediaFeedRepository, times(1)).saveAll(listOf(MediaFeed("", anyFeed)))
    }
}
