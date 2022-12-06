package advent2022

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

object A05b {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val lines = Files.readAllLines(Paths.get("a.txt"))
        val empty = A05a.findEmptyLine(lines)
        val stacks = A05a.readStacks(lines, empty)
        moveStacks(stacks, lines, empty)
        A05a.printTop(stacks)
    }

    private fun moveStacks(stacks: List<Stack<Char>?>, lines: List<String>, empty: Int) {
        for (i in empty + 1 until lines.size) {
            val parts = lines[i].split(' ')
            val count = parts[1].toInt()
            val src = stacks[parts[3].toInt()]
            val dst = stacks[parts[5].toInt()]
            moveWholeStack(count, src, dst)
        }
    }

    private fun moveWholeStack(count: Int, src: Stack<Char>?, dst: Stack<Char>?) {
        val tmp = Stack<Char>()
        for (x in 0 until count) {
            tmp.push(src?.pop())
        }
        for (x in 0 until count) {
            dst?.push(tmp.pop())
        }
    }

}
