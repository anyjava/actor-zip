package dev.anyjava.actorzip.common.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun convertDateTime(str: String, format: String = "yyyy-MM-dd HH:mm:ss"): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern(format)
    return LocalDateTime.parse(str, formatter)
}