package dev.anyjava.actorzip.adapter.presentation.controller

import dev.anyjava.actorzip.feed.domain.SiteType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SettingsController {

    @GetMapping("/settings/siteTypes")
    fun getSiteTypes(): List<SiteTypeResponse> {
        return SiteType.values()
            .map { SiteTypeResponse(it, it.siteName) }
            .toList()
    }
}

data class SiteTypeResponse(val siteType: SiteType, val siteName: String)