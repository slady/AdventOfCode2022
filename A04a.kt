package advent2022

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object A04a {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        println(Files.lines(Paths.get("a.txt")).mapToInt { line -> contains(line) }.sum())
    }

    private fun contains(line: String): Int {
        val b1 = line.indexOf('-')
        val b2 = line.indexOf(',')
        val b3 = line.indexOf('-', b2)
        val ax = line.substring(0, b1).toInt()
        val ay = line.substring(b1 + 1, b2).toInt()
        val bx = line.substring(b2 + 1, b3).toInt()
        val by = line.substring(b3 + 1).toInt()
        return if ((ax >= bx && ay <= by) || (bx >= ax && by <= ay)) 1 else 0
    }
}
