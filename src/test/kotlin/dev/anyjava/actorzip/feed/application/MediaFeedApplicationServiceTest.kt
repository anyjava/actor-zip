package dev.anyjava.actorzip.feed.application

import dev.anyjava.actorzip.feed.domain.*
import dev.anyjava.actorzip.feed.service.MediaFeedFinder
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import org.springframework.data.domain.PageRequest
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
            return listOf(MediaFeed(MediaType.AVI, "", anyFeed))
        }
    }

    lateinit var mediaFeedRepository: MediaFeedRepository

    lateinit var mediaFeedApplicationService: MediaFeedApplicationService

    @BeforeEach
    fun setup() {
        mediaFeedRepository = MediaFeedInMemoryRepository()
        mediaFeedApplicationService = MediaFeedApplicationService(listOf(mediaFeedFinder), mediaFeedRepository)
    }

    @Test
    fun `run`() {
        // given
        val feed = Feed("test3", "/service/board/park/16062401?od=T31&po=0&category=0&groupCd=", LocalDateTime.now())

        // when
        mediaFeedApplicationService.run(feed)

        // then
        mediaFeedRepository.findAll() shouldBe listOf(MediaFeed(MediaType.AVI,"", anyFeed))
    }

    @Test
    fun `get media feeds`() {
        // given
        val mediaFeed = MediaFeed(MediaType.IMAGE, "url", Feed("title", "/urlcontent", LocalDateTime.now()))
        mediaFeedRepository.saveAll(listOf(mediaFeed))

        // when
        val mediaFeeds = mediaFeedApplicationService.getMediaFeeds(PageRequest.of(0, 20))

        // then
        mediaFeeds.content.size shouldBe 1
        mediaFeeds.content[0].mediaType shouldBe MediaType.IMAGE
        mediaFeeds.content[0].originUrl shouldBe "https://clien.net/urlcontent"
    }
}
