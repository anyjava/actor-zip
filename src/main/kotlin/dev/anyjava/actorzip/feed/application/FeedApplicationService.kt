package dev.anyjava.actorzip.feed.application

import dev.anyjava.actorzip.adapter.presentation.controller.FeedsResponse
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class FeedApplicationService {

    fun getFeeds() : Slice<FeedsResponse> {

        val content = ArrayList<FeedsResponse>()
        content.add(FeedsResponse("게시글 1", "https://www.clien.net/service/board/park/15999808", LocalDateTime.now()))
        content.add(FeedsResponse("게시글 2", "https://www.clien.net/service/board/park/15999808", LocalDateTime.now()))

        return SliceImpl(content)
    }
}