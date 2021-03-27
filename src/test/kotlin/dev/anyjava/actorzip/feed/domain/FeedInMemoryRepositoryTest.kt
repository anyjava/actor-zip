package dev.anyjava.actorzip.feed.domain

import io.kotlintest.TestCase
import io.kotlintest.matchers.string.contain
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec
import org.springframework.data.domain.PageRequest

class FeedInMemoryRepositoryTest(var feedInMemoryRepository: FeedInMemoryRepository) : FunSpec({

    test("repository 에서 1 페이지의 10개 조회") {
        val pageSize = 10
        val list = feedInMemoryRepository.findAll(PageRequest.of(1, pageSize))
        list.size shouldBe pageSize
        list.get(list.size-1).title should contain("19")
    }

}) {

    constructor() : this(FeedInMemoryRepository())

    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        feedInMemoryRepository.setUp()
    }
}