package com.codingbot.shop.core.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random

fun RandomFromToDateMaker(
    year: Int = 2024,
    count: Int = 30
): List<Pair<LocalDate, LocalDate>> {

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val dateRanges = generateRandomDateRanges(year, count)

    // Print the generated date ranges
//    dateRanges.forEach { (from, to) ->
//        println("From: ${from.format(formatter)} To: ${to.format(formatter)}")
//    }
    return dateRanges
}

private fun generateRandomDateRanges(year: Int, count: Int): List<Pair<LocalDate, LocalDate>> {
    val dateRanges = mutableListOf<Pair<LocalDate, LocalDate>>()

    repeat(count) {
        val from = getRandomDateInYear(year)
        val to = getRandomDateInYear(year).let {
            if (it.isBefore(from)) from.plusDays(Random.nextLong(1, 60)) else it
        }
        dateRanges.add(Pair(from, to))
    }

    return dateRanges
}

private fun getRandomDateInYear(year: Int): LocalDate {
    val month = Random.nextInt(1, 13)
    val day = Random.nextInt(1, LocalDate.of(year, month, 1).lengthOfMonth() + 1)
    return LocalDate.of(year, month, day)
}