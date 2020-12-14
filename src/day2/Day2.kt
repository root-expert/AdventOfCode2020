package day2

import java.io.File

fun main(args: Array<String>) {
    val second = args.firstOrNull()?.let { it == "second" } ?: false
    var count = 0
    val input = File("input.txt").inputStream()

    input.bufferedReader().forEachLine {
        val split = it.split(" ")

        val range = split[0].split("-")
        val letter = split[1].first()

        count += solve(IntRange(range[0].toInt(), range[1].toInt()), letter, split[2], second)
    }

    println("Number of valid passwords: $count")
}

fun solve(range: IntRange, letter: Char, password: String, second: Boolean): Int {
    if (!second) {
        val count = password.filter { it == letter }.count()

        return if (range.contains(count))
            1
        else
            0
    } else {
        val first = password.elementAt(range.first - 1)
        val last = password.elementAt(range.last - 1)

        return if (first == letter && last == letter)
            0
        else if (first != letter && last != letter)
            0
        else
            1
    }
}
