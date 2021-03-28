package dev.anyjava.actorzip.common.utils

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class DateUtilsKtTest : FunSpec({

    test("convertDateTime") {
        val str = "2021-03-26 19:28:06"

        val dt = convertDateTime(str)

        dt shouldBe LocalDateTime.of(2021, 3, 26, 19, 28, 6)
    }

})
