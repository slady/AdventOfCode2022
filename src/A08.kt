import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object A08 {
    @Throws(IOException::class)
    @JvmStatic
    fun main(a: Array<String>) {
        val lines = Files.readAllLines(Paths.get("a.txt"))
        val h = lines.size
        val w = lines[0].length
        val array = Array(h) { IntArray(w) }

        for (y in 0 until h) {
            val line = lines[y]
            for (x in 0 until w) {
                array[y][x] = line[x].code - '0'.code
            }
        }

        var sum = (w + h) * 2 - 4
        for (y in 1 until h - 1) {
            for (x in 1 until w - 1) {
                if (isVisible(x, y, array, w, h)) {
                    sum++
                }
            }
        }
        println(sum)

        var max = 0
        for (y in 1 until h - 1) {
            for (x in 1 until w - 1) {
                val score = score(x, y, array, w, h)
                if (score > max) {
                    max = score
                }
            }
        }
        println(max)
    }

    private fun score(x: Int, y: Int, array: Array<IntArray>, w: Int, h: Int): Int {
        val value = array[y][x]

        var left = 1
        for (i in x - 1 downTo 1) {
            if (array[y][i] >= value) {
                break
            }
            left++
        }

        var right = 1
        for (i in x + 1 until w - 1) {
            if (array[y][i] >= value) {
                break
            }
            right++
        }

        var up = 1
        for (i in y - 1 downTo 1) {
            if (array[i][x] >= value) {
                break
            }
            up++
        }

        var down = 1
        for (i in y + 1 until h - 1) {
            if (array[i][x] >= value) {
                break
            }
            down++
        }

        return left * right * up * down
    }

    private fun isVisible(x: Int, y: Int, array: Array<IntArray>, w: Int, h: Int): Boolean {
        val value = array[y][x]

        // left
        var test = true
        for (i in 0 until x) {
            if (array[y][i] >= value) {
                test = false
                break
            }
        }
        if (test) {
            return true
        }

        // right
        test = true
        for (i in x + 1 until w) {
            if (array[y][i] >= value) {
                test = false
                break
            }
        }
        if (test) {
            return true
        }

        // top
        test = true
        for (i in 0 until y) {
            if (array[i][x] >= value) {
                test = false
                break
            }
        }
        if (test) {
            return true
        }

        // bottom
        test = true
        for (i in y + 1 until h) {
            if (array[i][x] >= value) {
                test = false
                break
            }
        }
        return test
    }
}
