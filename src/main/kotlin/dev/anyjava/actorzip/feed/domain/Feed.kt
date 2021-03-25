package dev.anyjava.actorzip.feed.domain

import java.time.LocalDateTime

class Feed(
    var title: String,
    var url: String,
    var registrationDateTime: LocalDateTime
) {
}