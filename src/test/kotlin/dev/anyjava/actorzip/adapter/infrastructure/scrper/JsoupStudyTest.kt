package dev.anyjava.actorzip.adapter.infrastructure.scrper

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.comparables.beGreaterThan
import io.kotest.matchers.should
import org.jsoup.Jsoup

class JsoupStudyTest : FunSpec({
    test("Jsoup: 단순 html 조회") {
        val html = Jsoup.connect("https://www.clien.net/service/search?q=%ED%95%9C%EC%98%88%EB%A6%AC").get()

        html.toString().length should beGreaterThan(0)
    }

}) {

}