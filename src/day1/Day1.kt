import java.io.File

fun main() {
    val input = File("input.txt").inputStream()
    val numbers = HashSet<Int>()

    input.bufferedReader().forEachLine {
        numbers.add(it.toInt())
    }

    numbers.forEach {
        val remain = 2020 - it

        if (numbers.contains(remain)) {
            val product = remain * it
            println("$it and $remain with product: $product")
            return
        }
    }

    println("Pair not found! BRUH check your code.")
}
