package dev.anyjava.actorzip.adapter.infrastructure.scrper

import dev.anyjava.actorzip.common.utils.convertDateTime
import dev.anyjava.actorzip.feed.domain.Feed
import dev.anyjava.actorzip.feed.domain.MediaFeed
import dev.anyjava.actorzip.feed.domain.MediaType
import dev.anyjava.actorzip.feed.domain.SiteType
import dev.anyjava.actorzip.feed.service.FeedScraper
import dev.anyjava.actorzip.feed.service.MediaFeedFinder
import mu.KotlinLogging
import org.jsoup.Jsoup
import org.springframework.stereotype.Component
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {}

@Component
class ClienScrapper : FeedScraper {

    val SITE_TYPE = SiteType.CLIEN

    override fun accept(siteType: SiteType): Boolean {
        return siteType == SITE_TYPE
    }

    override fun scrap(lastDateTime: LocalDateTime): List<Feed> {
        val document = Jsoup.connect("https://www.clien.net/service/search?q=%ED%95%9C%EC%98%88%EB%A6%AC").get()
        return document.select(".list_item").asSequence()
            .map {
                Feed(
                    it.select(".subject_fixed").attr("title"),
                    it.select(".subject_fixed").attr("href"),
                    convertDateTime(it.select(".timestamp").text())
                )
            }
            .filter { it.registrationDateTime > lastDateTime }
            .onEach { logger.debug("clien = $it") }
            .toList()
    }
}

@Component
class ClienMediaImageScrapper : MediaFeedFinder {

    override fun acceptSite(feed: Feed): Boolean {
        return feed.siteType == SiteType.CLIEN
    }

    override fun accept(feed: Feed): Boolean {
        logger.info(">> ${feed.getContentUrl()}")
        val document = Jsoup.connect(feed.getContentUrl()).get()
        return document.select(".post_article").asSequence()
            .map { it.select("img") }
            .flatMap { it }
            .any()
    }

    override fun scrap(feed: Feed): List<MediaFeed> {
        val document = Jsoup.connect(feed.getContentUrl()).get()
        return document.select(".post_article").asSequence()
            .map { it.select("img") }
            .flatMap { it }
            .map { MediaFeed(MediaType.IMAGE, it.attr("src"), feed) }
            .toList()
    }
}

@Component
class ClienMediaYoutubeScrapper : MediaFeedFinder {

    override fun acceptSite(feed: Feed): Boolean {
        return feed.siteType == SiteType.CLIEN
    }

    override fun accept(feed: Feed): Boolean {
        logger.info(">> ${feed.getContentUrl()}")
        val document = Jsoup.connect(feed.getContentUrl()).get()
        return document.select(".post_article").asSequence()
            .map { it.select(".video") }
            .flatMap { it }
            .filter { it.select("iframe").attr("src").contains("youtube.com") }
            .any()
    }

    override fun scrap(feed: Feed): List<MediaFeed> {
        val document = Jsoup.connect(feed.getContentUrl()).get()
        return document.select(".post_article").asSequence()
            .map { it.select(".video") }
            .flatMap { it }
            .filter { it.select("iframe").attr("src").contains("youtube.com") }
            .map { MediaFeed(MediaType.YOUTUBE, it.select("iframe").attr("src"), feed) }
            .toList()
    }
}

@Component
class ClienMediaAviScrapper : MediaFeedFinder {

    override fun acceptSite(feed: Feed): Boolean {
        return feed.siteType == SiteType.CLIEN
    }

    override fun accept(feed: Feed): Boolean {
        logger.info(">> ${feed.getContentUrl()}")
        val document = Jsoup.connect(feed.getContentUrl()).get()
        return document.select(".post_article").asSequence()
            .map { it.select("video") }
            .flatMap { it }
            .any()
    }

    override fun scrap(feed: Feed): List<MediaFeed> {
        val document = Jsoup.connect(feed.getContentUrl()).get()
        return document.select(".post_article").asSequence()
            .map { it.select("video") }
            .flatMap { it }
            .map { MediaFeed(MediaType.AVI, it.select("source").attr("src"), feed, it.attr("poster")) }
            .toList()
    }
}