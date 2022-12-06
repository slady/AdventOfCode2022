package advent2022

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object A03a {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        println(Files.lines(Paths.get("a.txt")).mapToInt { line -> letterToInt(findLetter(line)) }.sum())
    }

    fun letterToInt(letter: Char): Int {
        return if (letter > '`') letter - '`'
        else letter - '&'
    }

    private fun findLetter(line: String): Char {
        val characters: MutableSet<Char> = HashSet()
        val half = line.length / 2
        for (i in 0 until half) {
            characters.add(line[i])
        }
        for (i in half until line.length) {
            val ch = line[i]
            if (characters.contains(ch)) {
                return ch
            }
        }
        throw RuntimeException()
    }
}
