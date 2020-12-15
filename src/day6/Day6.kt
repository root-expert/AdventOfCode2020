package day6

import java.io.File

fun main(args: Array<String>) {
    val second = args.firstOrNull()?.let { it == "second" } ?: false
    val input = File("input.txt")

    val groups = input.readText().split("\n\n")
    var count = 0

    count = if (!second)
        countValidQuestions(groups)
    else
        countValidSame(groups)

    println("Count = $count")
}

fun countValidQuestions(groups: List<String>): Int {
    var count = 0
    val answers = mutableSetOf<Char>()

    groups.forEach {
        it.replace("\n", "").forEach { answer ->
            answers.add(answer)
        }
        count += answers.size
        answers.clear()
    }

    return count
}

fun countValidSame(groups: List<String>): Int {
    var count = 0
    val answers = mutableMapOf<Char, Int>()

    groups.forEach {
        val people = it.sumBy { char -> if (char == '\n') 1 else 0 } + 1

        it.replace("\n", "").forEach { answer ->
            if (answers.containsKey(answer))
                answers[answer] = answers[answer]?.plus(1) ?: 1
            else
                answers[answer] = 1
        }
        answers.forEach loop@{ number ->
            if (number.value == people)
                count += 1
        }
        answers.clear()
    }

    return count
}
