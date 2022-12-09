import java.io.IOException
import java.nio.file.Paths
import java.util.*
import kotlin.math.abs

internal class Creeper {
    var x = 250
    var y = 250

    fun step(leader: Creeper) {
        val dx = leader.x - x
        val dy = leader.y - y

        if (abs(dx) != 2 && abs(dy) != 2) {
            return
        }

        x += step(dx)
        y += step(dy)
    }

    private fun step(x: Int): Int {
        return if (x < 0) -1 else if (x > 0) 1 else 0
    }
}

object A09a {
    @Throws(IOException::class)
    @JvmStatic
    fun main(a: Array<String>) {
        run(2)
        run(10)
    }

    @Throws(IOException::class)
    fun run(r: Int) {
        val scanner = Scanner(Paths.get("a.txt"))
        val array = Array(500) { BooleanArray(500) }

        val c = Array(r) { Creeper() }

        val h = c[0]
        val t = c[r - 1]
        array[t.x][t.y] = true

        while (scanner.hasNext()) {
            val d = scanner.next()[0]
            val s = scanner.nextInt()

            for (i in 0 until s) {
                when (d) {
                    'L' -> h.x--
                    'R' -> h.x++
                    'U' -> h.y++
                    'D' -> h.y--
                }

                for (j in 1 until r) {
                    c[j].step(c[j - 1])
                }

                array[t.x][t.y] = true
            }
        }

        var sum = 0
        for (x in array.indices) {
            for (y in array[0].indices) {
                if (array[x][y]) {
                    sum++
                }
            }
        }
        println(sum)
    }
}
