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

enum class SiteType(val host: String) {
    CLIEN("https://clien.net")
}
