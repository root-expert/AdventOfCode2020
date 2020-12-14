package day4

import java.io.File

fun main(args: Array<String>) {
    val second = args.firstOrNull()?.let { it == "second" } ?: false
    var count = 0

    val passports = read()

    if (!second) {
        passports.forEach {
            val isValid = validKeys(it)
            if (isValid) count++
        }
    } else {
        passports.forEach {
            val isValid = validKeyAndValues(it)
            if (isValid) count++
        }
    }

    println("Number of valid passports: $count")
}

fun validKeys(passport: MutableMap<String, String>): Boolean {
    if (passport.size == 8)
        return true
    else if (passport.size == 7 && !passport.containsKey("cid"))
        return true

    return false
}

fun validKeyAndValues(passport: MutableMap<String, String>): Boolean {
    if (!validKeys(passport)) return false
    val byr = passport["byr"]!!.toIntOrNull() ?: return false
    if (byr < 1920 || byr > 2002) return false

    val iyr = passport["iyr"]!!.toIntOrNull() ?: return false
    if (iyr < 2010 || iyr > 2020) return false

    val eyr = passport["eyr"]!!.toIntOrNull() ?: return false
    if (eyr < 2020 || eyr > 2030) return false

    val hgt = passport["hgt"]!!

    val cm = Regex("(\\d+)cm")
    val `in` = Regex("(\\d+)in")

    if (cm.matches(hgt)) {
        val height = cm.matchEntire(hgt)!!.groups[1]!!.value.toInt()
        if (height < 150 || height > 193) return false
    } else if (`in`.matches(hgt)) {
        val height = `in`.matchEntire(hgt)!!.groups[1]!!.value.toInt()
        if (height < 59 || height > 76) return false
    } else return false

    val hcl = passport["hcl"]!!
    if (!hcl.contains(Regex("#[0-9a-f]{6}"))) return false

    val ecl = passport["ecl"]!!
    if (!ecl.contains(Regex("(amb|blu|brn|gry|grn|hzl|oth)"))) return false

    val pid = passport["pid"]!!
    if (!pid.contains(Regex("^\\d{9}$"))) return false

    return true
}

fun read(): MutableList<MutableMap<String, String>> {
    val input = File("input.txt")
    val passports = mutableListOf<MutableMap<String, String>>()

    input.readText().split("\n\n").forEach {
        val codes = mutableMapOf<String, String>()
        val formatted = it.replace("\n", " ")
        val split = formatted.split(" ")

        split.forEach { code ->
            val keyValue = code.split(":")
            codes[keyValue.first()] = keyValue.last()
        }
        passports.add(codes)
    }

    return passports
}
