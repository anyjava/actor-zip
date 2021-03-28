package dev.anyjava.actorzip.infrastructure.scrper

import dev.anyjava.actorzip.common.utils.convertDateTime
import dev.anyjava.actorzip.feed.domain.Feed
import dev.anyjava.actorzip.feed.domain.SiteType
import dev.anyjava.actorzip.feed.service.FeedScraper
import org.jsoup.Jsoup
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DcScrapper : FeedScraper {

    val SITE_TYPE = SiteType.DC

    override fun accept(siteType: SiteType): Boolean {
        return siteType == SITE_TYPE
    }

    override fun scrap(lastDateTime: LocalDateTime): List<Feed> {
        val document = Jsoup.connect("https://gall.dcinside.com/mgallery/board/lists/?id=hanyeri&page=1").get()

        return document.select(".ub-content").asSequence()
            .filter { it.attr("data-type") != "icon_notice" }
            .map { Feed(
                it.select(".gall_tit").select("a").text(),
                it.select(".gall_tit").select("a").attr("href"),
                convertDateTime(it.select(".gall_date").attr("title")),
                SiteType.DC)
            }
            .filter { it.registrationDateTime > lastDateTime }
            .toList()
    }
}
