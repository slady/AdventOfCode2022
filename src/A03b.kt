import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object A03b {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val lines = Files.readAllLines(Paths.get("a.txt"))
        var score = 0
        var g = 0
        while (g < lines.size) {
            score += A03a.letterToInt(findLetter(lines[g++], lines[g++], lines[g++]))
        }
        println(score)
    }

    private fun findLetter(s0: String, s1: String, s2: String): Char {
        val characters1: MutableCollection<Char> = HashSet()
        characters1.addAll(s0.toSet())
        characters1.retainAll(s1.toSet())
        characters1.retainAll(s2.toSet())
        return characters1.elementAt(0)
    }

    private fun findLetterX(s0: String, s1: String, s2: String): Char {
        val characters1: MutableSet<Char> = HashSet()
        for (ch in s0) {
            characters1.add(ch)
        }
        val characters2: MutableSet<Char> = HashSet()
        for (ch in s1) {
            if (characters1.contains(ch)) {
                characters2.add(ch)
            }
        }
        for (ch in s2) {
            if (characters2.contains(ch)) {
                return ch
            }
        }
        throw RuntimeException()
    }
}
