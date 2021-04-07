package dev.anyjava.actorzip.feed.domain

import java.time.LocalDateTime

class Feed(
    var title: String,
    var url: String,
    var registrationDateTime: LocalDateTime,
    var siteType: SiteType = SiteType.CLIEN,
) {

    override fun toString(): String {
        return "Feed(title='$title', url='$url', siteType=$siteType, registrationDateTime=$registrationDateTime)"
    }

    fun getContentUrl(): String {
        return siteType.host + this.url
    }
}

enum class SiteType(val siteName: String, val host: String) {
    CLIEN("클리앙", "https://clien.net"),
    DC("디씨인사이드", "https://gall.dcinside.com")
}
