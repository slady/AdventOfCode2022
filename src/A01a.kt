import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object A01a {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val lines = Files.readAllLines(Paths.get("a.txt"))
        var sum = 0
        var max = 0
        for (line in lines) {
            if (line.isEmpty()) {
                if (sum > max) {
                    max = sum
                }
                sum = 0
            } else {
                sum += line.toInt()
            }
        }
        println(max)
    }
}
