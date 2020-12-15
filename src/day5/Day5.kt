package day5

import java.io.File

fun main(args: Array<String>) {
    val second = args.firstOrNull()?.let { it == "second" } ?: false
    val input = File("input.txt").inputStream()

    val seats = mutableListOf<Int>()
    var rowLow = 0
    var rowHigh = 128
    var colLow = 0
    var colHigh = 8

    var highest = -1

    input.bufferedReader().forEachLine {
        it.forEach { code ->
            when (code) {
                'F' -> rowHigh -= (rowHigh - rowLow) / 2
                'B' -> rowLow += (rowHigh -  rowLow) / 2
                'L' -> colHigh -= (colHigh - colLow) / 2
                'R' -> colLow += (colHigh - colLow) / 2
            }
        }
        val sid = rowLow * 8 + colLow
        if (sid > highest) highest = sid
        seats.add(sid)
        rowLow = 0
        rowHigh = 128
        colLow = 0
        colHigh = 8
    }

    println("Highest sid = $highest")

    if (!second)
        return

    seats.sort()
    var lastSeat = seats[0]
    seats.forEach {
        if (it - lastSeat > 1)
            println("Your seat = ${it - 1}")
        lastSeat = it
    }
}
