import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.math.abs

object A15 {
    @Throws(IOException::class)
    @JvmStatic
    fun main(a: Array<String>) {
        val scanners = Files.lines(Paths.get("s")).map { s: String -> Scanner(s) }.toList()
        val intervals = scanners.stream().map { s -> s.createInterval(2000000) }
            .filter { obj -> Objects.nonNull(obj) }.sorted(Comparator.comparingInt { i -> i!!.s }).toList()

        println(mergeIntervals(intervals).stream().mapToInt { obj -> obj!!.size }.sum() - 1)

        for (y in 0..4000000) {
            val intervalList = mergeIntervals(scanners.stream().map { s -> s.createInterval(y) }
                .filter { obj -> Objects.nonNull(obj) }.sorted(Comparator.comparingInt { i -> i!!.s }).toList())
            if (intervalList.size != 1) {
                println((intervalList[0].e + 1L) * 4000000 + y)
            }
        }
    }

    private fun mergeIntervals(arr: List<Interval?>): List<Interval> {
        val stack = Stack<Interval>()
        stack.push(arr[0])

        for (i in 1 until arr.size) {
            val current = arr[i]
            val top = stack.peek()

            if (top!!.e < current!!.s)
                stack.push(current)
            else if (top.e < current.e) {
                top.e = current.e
                stack.pop()
                stack.push(top)
            }
        }
        return stack
    }
}

class Interval(var s: Int, var e: Int) {
    val size: Int
        get() = 1 + e - s
}

internal class Scanner(s: String) {
    private val sx: Int
    private val sy: Int
    private val r: Int

    init {
        val q = s.indexOf('=') + 1
        val w = s.indexOf(',', q)
        sx = s.substring(q, w).toInt()
        val e = s.indexOf('=', w) + 1
        val h = s.indexOf(':', e)
        sy = s.substring(e, h).toInt()
        val t = s.indexOf('=', h) + 1
        val y = s.indexOf(',', t)
        val bx = s.substring(t, y).toInt()
        val u = s.indexOf('=', y) + 1
        val by = s.substring(u).toInt()
        r = abs(sx - bx) + abs(sy - by)
    }

    fun createInterval(row: Int): Interval? {
        val t = r - abs(sy - row)
        if (t < 0) return null
        return Interval(sx - t, sx + t)
    }
}
