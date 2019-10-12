package com.example.paul.blog.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.*

private val daysLookup = (1..31).associate {
    it.toLong() to getOrdinal(it)
}

private fun getOrdinal(n: Int) = when {
    n in 11..13 -> "${n}th"
    n % 10 == 1 -> "${n}st"
    n % 10 == 2 -> "${n}nd"
    n % 10 == 3 -> "${n}rd"
    else -> "${n}th"
}

fun LocalDateTime.format(): String = this.format(englishDateFormatter)

private val englishDateFormatter = DateTimeFormatterBuilder()
        .appendPattern("yyyy-MM-dd")
        .appendPattern(" ")
        .appendText(ChronoField.DAY_OF_MONTH, daysLookup)
        .appendPattern(" ")
        .appendPattern("yyyy")
        .toFormatter(Locale.ENGLISH)

fun String.toSlug() = toLowerCase()
        .replace("\n", " ")
        .replace("[^a-z\\d\\s]".toRegex(), " ")
        .split(" ")
        .joinToString("-")
        .replace("-+".toRegex(), "-")
