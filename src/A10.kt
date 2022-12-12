import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object A10 {
    @Throws(IOException::class)
    @JvmStatic
    fun main(a: Array<String>) {
        var c = 0
        var x = 1
        var s = 0
        var p = 0

        Files.lines(Paths.get("a.txt")).forEach {
            for (l in 0..(if ("noop" == it) 0 else 1)) {
                if (++c % 20 == 0 && c % 40 != 0) {
                    s += c * x
                }

                print(if (x - 1 <= p && p <= x + 1) '#' else '.')

                if (++p == 40) {
                    p = 0
                    println()
                }

                if (l == 1) {
                    x += it.substring(5).toInt()
                }
            }
        }
        println(s)
    }
}
