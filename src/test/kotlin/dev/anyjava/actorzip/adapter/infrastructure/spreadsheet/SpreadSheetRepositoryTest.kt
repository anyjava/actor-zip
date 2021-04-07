package dev.anyjava.actorzip.adapter.infrastructure.spreadsheet

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class SpreadSheetRepositoryTest(val spreadSheetRepository: SpreadSheetRepository) : FunSpec({

    test("get google sh") {
        spreadSheetRepository.run()
    }

}) {
    constructor() : this(SpreadSheetRepository())
}

