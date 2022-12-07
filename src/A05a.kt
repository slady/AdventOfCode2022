import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

object A05a {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val lines = Files.readAllLines(Paths.get("a.txt"))
        val empty = findEmptyLine(lines)
        val stacks = readStacks(lines, empty)
        moveStacks(stacks, lines, empty)
        printTop(stacks)
    }

    fun printTop(stacks: List<Stack<Char>?>) {
        for (i in 1 until stacks.size) {
            val stack = stacks[i]
            print(stack!!.peek())
        }
    }

    fun moveStacks(stacks: List<Stack<Char>?>, lines: List<String>, empty: Int) {
        for (i in empty + 1 until lines.size) {
            val parts = lines[i].split(' ')
            val count = parts[1].toInt()
            val src = stacks[parts[3].toInt()]
            val dst = stacks[parts[5].toInt()]
            moveStacksSingle(count, src, dst)
        }
    }

    private fun moveStacksSingle(count: Int, src: Stack<Char>?, dst: Stack<Char>?) {
        for (i in 0 until count) {
            dst!!.push(src!!.pop())
        }
    }

    fun readStacks(lines: List<String>, empty: Int): List<Stack<Char>?> {
        val stacks: MutableList<Stack<Char>?> = ArrayList()
        stacks.add(null)
        val stackCount = (lines[empty - 1].length + 2) / 4
        for (i in 0 until stackCount) {
            stacks.add(Stack())
        }
        for (i in empty - 2 downTo 0) {
            val line = lines[i]
            for (s in 0 until stackCount) {
                val pos = 1 + 4 * s
                if (pos > line.length) {
                    break
                }
                val ch = line[pos]
                if (ch == ' ') {
                    continue
                }
                stacks[s + 1]!!.push(ch)
            }
        }
        return stacks
    }

    fun findEmptyLine(lines: List<String>): Int {
        for (i in lines.indices) {
            if (lines[i].isEmpty()) {
                return i
            }
        }
        throw RuntimeException()
    }
}
