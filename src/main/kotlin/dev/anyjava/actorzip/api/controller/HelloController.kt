package dev.anyjava.actorzip.api.controller

import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello() : String {
        return "hello world"
    }

    @GetMapping("/feeds")
    fun getFeeds() : Slice<FeedsResponse> {
        val content = ArrayList<FeedsResponse>()
        content.add(FeedsResponse("게시글 1", LocalDateTime.now()))
        content.add(FeedsResponse("게시글 2", LocalDateTime.now()))

        return SliceImpl(content)
    }
}

data class FeedsResponse(val title: String, val registrationDateTime: LocalDateTime)
