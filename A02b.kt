package advent2022

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object A02b {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        println(Files.lines(Paths.get("a.txt")).mapToInt { line -> when (line) {
            "A X" -> 3
            "A Y" -> 4
            "A Z" -> 8
            "B X" -> 1
            "B Y" -> 5
            "B Z" -> 9
            "C X" -> 2
            "C Y" -> 6
            "C Z" -> 7
            else -> throw RuntimeException()
        } }.sum())
    }
}
