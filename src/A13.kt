import Result.*
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.IntNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

enum class Result(val i: Int) {
    RIGHT(-1), NOT_RIGHT(1), SAME(0)
}

object A13 {
    private val mapper = jacksonObjectMapper()

    @Throws(IOException::class)
    @JvmStatic
    fun main(a: Array<String>) {
        val lines = Files.readAllLines(Paths.get("a"))
        var s = 0

        for (i in 0..lines.size / 3) {
            if (nodesRight(lines[i * 3], lines[i * 3 + 1]) == RIGHT) {
                s += i + 1
            }
        }

        println(s)

        val key1 = "[[2]]"
        val key2 = "[[6]]"

        lines.add(key1)
        lines.add(key2)

        val collect = lines.stream().filter { f -> f.isNotEmpty() }.sorted { left, right -> (nodesRight(left, right)).i }.collect(Collectors.toList())

        val position1 = collect.indexOf(key1) + 1
        val position2 = collect.indexOf(key2) + 1

        println(position1 * position2)
    }

    private fun nodesRight(left: String, right: String): Result {
        return nodesRight(mapper.readValue(left, JsonNode::class.java), mapper.readValue(right, JsonNode::class.java))
    }

    private fun nodesRight(left: JsonNode, right: JsonNode): Result {
        if (left is IntNode && right is IntNode)
            return if (left.intValue() < right.intValue()) return RIGHT else if (left.intValue() > right.intValue()) NOT_RIGHT else SAME
        else if (left is ArrayNode && right is ArrayNode)
            return arraysRight(left, right)
        else if (left is IntNode && right is ArrayNode)
            return arraysRight(mapper.createArrayNode().add(left), right)
        else if (left is ArrayNode && right is IntNode)
            return arraysRight(left, mapper.createArrayNode().add(right))

        throw RuntimeException()
    }

    private fun arraysRight(left: ArrayNode, right: ArrayNode): Result {
        for (i in 0 until left.size()) {
            if (i >= right.size()) {
                return NOT_RIGHT
            }

            val r = nodesRight(left.get(i), right.get(i))

            if (r != SAME)
                return r
        }

        if (right.size() > left.size())
            return RIGHT

        return SAME
    }
}
