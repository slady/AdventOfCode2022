import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object A12a {
    @Throws(IOException::class)
    @JvmStatic
    fun main(a: Array<String>) {
        val puzzle = Puzzle()
        println(findPath(puzzle, puzzle.sx, puzzle.sy))

        val lengths = ArrayList<Int>()

        for (sx in 0 until puzzle.w) {
            for (sy in 0 until puzzle.h) {
                if (puzzle.m[sx][sy] != 0) {
                    continue
                }

                lengths.add(findPath(puzzle, sx, sy))
            }
        }

        println(lengths.stream().sorted().findFirst().get())
    }

    private fun findPath(puzzle: Puzzle, sx: Int, sy: Int): Int {
        puzzle.v[sx][sy] = true
        val front = ArrayList<Step>()
        front.add(Step(sx, sy))
        var stepCounter = 0

        do {
            val nextLine: MutableList<Step> = ArrayList()

            for (current in front) {
                val cx = current.x
                val cy = current.y
                val v = puzzle.m[cx][cy]

                if (cx > 0) {
                    makeStep(puzzle, v, current, cx - 1, cy)
                }
                if (cy > 0) {
                    makeStep(puzzle, v, current, cx, cy - 1)
                }
                if (cx < puzzle.w - 1) {
                    makeStep(puzzle, v, current, cx + 1, cy)
                }
                if (cy < puzzle.h - 1) {
                    makeStep(puzzle, v, current, cx, cy + 1)
                }

                nextLine.addAll(current.nextSteps)
            }

            if (nextLine.isEmpty()) {
                return Int.MAX_VALUE
            }

            front.clear()
            front.addAll(nextLine)

            stepCounter++
        } while (!front.stream().anyMatch { s: Step -> puzzle.ex == s.x && puzzle.ey == s.y })

        puzzle.clear()
        return stepCounter
    }

    private fun makeStep(puzzle: Puzzle, v: Int, step: Step, x: Int, y: Int) {
        if (puzzle.v[x][y] || puzzle.m[x][y] > v + 1) {
            return
        }

        puzzle.v[x][y] = true
        step.nextSteps.add(Step(x, y))
    }
}

internal class Puzzle {
    val m: Array<IntArray>
    val v: Array<BooleanArray>
    val w: Int
    val h: Int
    var sx = 0
    var sy = 0
    var ex = 0
    var ey = 0

    init {
        val lines = Files.readAllLines(Paths.get("m"))
        w = lines[0].length
        h = lines.size
        m = Array(w) { IntArray(h) }
        v = Array(w) { BooleanArray(h) }
        for (y in 0 until h) {
            val line = lines[y]

            for (x in 0 until w) {
                var c = line[x]

                if (c == 'S') {
                    c = 'a'
                    sx = x
                    sy = y
                } else if (c == 'E') {
                    c = 'z'
                    ex = x
                    ey = y
                }

                m[x][y] = c.code - 'a'.code
            }
        }
    }

    fun clear() {
        for (x in 0 until w) {
            for (y in 0 until h) {
                v[x][y] = false
            }
        }
    }
}

internal class Step(val x: Int, val y: Int) {
    var nextSteps: MutableList<Step> = ArrayList()
}
