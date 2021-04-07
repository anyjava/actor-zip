package dev.anyjava.actorzip.adapter.presentation.controller

import dev.anyjava.actorzip.feed.application.FeedApplicationService
import dev.anyjava.actorzip.feed.domain.MediaType
import dev.anyjava.actorzip.feed.domain.SiteType
import io.swagger.annotations.ApiParam
import mu.KotlinLogging
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {}

@RestController
class FeedController(private val feedApplicationService: FeedApplicationService) {

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
        return feedApplicationService.getFeeds(excludeSiteTypes?: setOf(), pageRequest)
    }

    @GetMapping("/mediaFeeds")
    fun getImageFeeds(pageRequest: Pageable) : Slice<ImageFeedResponse> {
        return SliceImpl(listOf(
            ImageFeedResponse(SiteType.CLIEN, MediaType.IMAGE, "https://dcimg3.dcinside.co.kr/viewimage.php?id=25bcde3fe0c031&no=24b0d769e1d32ca73cec80fa11d028312e15c0eaac8534358234c142d07c64874afc8dd2382c3fb54b43b4dc561a2d82af4e5c9fca74a6f034de1c070f921a5506c8dfabfcae04dfb07130343444512cdfc28745e6", "http://clien.net"),
            ImageFeedResponse(SiteType.CLIEN, MediaType.AVI, "https://img.anyjava.net/upload/2021-04-07/16037068-2.mp4", "http://clien.net"),
            ImageFeedResponse(SiteType.CLIEN, MediaType.YOUTUBE, "https://www.youtube.com/watch?v=gi9btzXLtiw", "http://clien.net"),
            ImageFeedResponse(SiteType.CLIEN, MediaType.IMAGE, "https://dcimg3.dcinside.co.kr/viewimage.php?id=25bcde3fe0c031&no=24b0d769e1d32ca73cec80fa11d028312e15c0eaac8534358234c142d07c64874afc8dd2382c3fb54b43b4dc561a2d82af4e5c9fca74a6f034de1c070f921a5506c8dfabfcae04dfb07130343444512cdfc28745e6", "http://clien.net"),
            ImageFeedResponse(SiteType.CLIEN, MediaType.IMAGE, "https://dcimg3.dcinside.co.kr/viewimage.php?id=25bcde3fe0c031&no=24b0d769e1d32ca73cec80fa11d028312e15c0eaac8534358234c142d07c64874afc8dd2382c3fb54b43b4dc561a2d82af4e5c9fca74a6f034de1c070f921a5506c8dfabfcae04dfb07130343444512cdfc28745e6", "http://clien.net"),
            ImageFeedResponse(SiteType.CLIEN, MediaType.AVI, "https://img.anyjava.net/upload/2021-04-07/16037068-2.mp4", "http://clien.net"),
            ImageFeedResponse(SiteType.CLIEN, MediaType.YOUTUBE, "https://www.youtube.com/watch?v=gi9btzXLtiw", "http://clien.net"),
            ImageFeedResponse(SiteType.CLIEN, MediaType.IMAGE, "https://dcimg3.dcinside.co.kr/viewimage.php?id=25bcde3fe0c031&no=24b0d769e1d32ca73cec80fa11d028312e15c0eaac8534358234c142d07c64874afc8dd2382c3fb54b43b4dc561a2d82af4e5c9fca74a6f034de1c070f921a5506c8dfabfcae04dfb07130343444512cdfc28745e6", "http://clien.net"),
            ImageFeedResponse(SiteType.CLIEN, MediaType.AVI, "https://img.anyjava.net/upload/2021-04-07/16037068-2.mp4", "http://clien.net"),
            ImageFeedResponse(SiteType.CLIEN, MediaType.YOUTUBE, "https://www.youtube.com/watch?v=gi9btzXLtiw", "http://clien.net"),
        ))
    }

}

data class FeedsResponse(val title: String, val siteType: SiteType, val url: String, val registrationDateTime: LocalDateTime)

data class ImageFeedResponse(val siteType: SiteType, val mediaType: MediaType, val imageUrl: String, val originUrl: String)