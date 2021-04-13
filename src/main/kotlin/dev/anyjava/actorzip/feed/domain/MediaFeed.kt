package dev.anyjava.actorzip.feed.domain

class MediaFeed(val url: String, val thumbnail: String = "") {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MediaFeed

        if (url != other.url) return false
        if (thumbnail != other.thumbnail) return false

        return true
    }

    override fun hashCode(): Int {
        var result = url.hashCode()
        result = 31 * result + thumbnail.hashCode()
        return result
    }
}