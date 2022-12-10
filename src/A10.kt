import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object A10 {
    @Throws(IOException::class)
    @JvmStatic
    fun main(a: Array<String>) {
        val lines = Files.readAllLines(Paths.get("a.txt"))
        var c = 0
        var x = 1
        var s = 0
        var p = 0
        var f = false

        for (line in lines) {
            for (l in 0..1) {
                if (++c % 20 == 0) {
                    f = !f
                    if (f) {
                        s += c * x
                    }
                }

                if (x - 1 <= p && p <= x + 1) {
                    print('#')
                } else {
                    print('.')
                }

                p++

                if (c % 40 == 0) {
                    p = 0
                    println()
                }

                if ("noop" == line) {
                    break
                }

                if (l == 0) {
                    continue
                }

                x += line.substring(5).toInt()
            }
        }
        println(s)
    }
}
