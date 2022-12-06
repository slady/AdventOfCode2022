package advent2022

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

object A01b {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val lines = Files.readAllLines(Paths.get("a.txt"))
        val sums = ArrayList<Int?>()
        var sum = 0
        for (line in lines) {
            if (line.isEmpty()) {
                sums.add(sum)
                sum = 0
            } else {
                sum += line.toInt()
            }
        }
        val sort = sums.toTypedArray()
        sort.sort()
        sort.reverse()
        val max = sort[0]!! + sort[1]!! + sort[2]!!
        println(max)
    }
}
