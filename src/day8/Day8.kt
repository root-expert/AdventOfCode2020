package day8

import java.io.File

fun main(args: Array<String>) {
    val second = args.firstOrNull()?.let { it == "second" } ?: false
    val input = File("input.txt")
    val program = input.readText().split("\n")
    val executed = mutableSetOf<Int>()

    val size = program.size
    var acc = 0
    var pc = 0 // Program counter

    do {
        if (pc > size || pc < 0)
            break

        val whole = program[pc]
        val cmd = whole.split(" ").first()
        val sign = whole.split(" ")[1].first()
        val offset = whole.split(" ")[1].substring(1).toInt()

        if (executed.contains(pc))
            break

        executed.add(pc)
        when (cmd) {
            "acc" -> {
                if (sign == '+') acc += offset else acc -= offset
                pc++
            }
            "jmp" -> if (sign == '+') pc += offset else pc -= offset
            "nop" -> pc++
        }
    } while (true)

    println("acc = $acc")
}