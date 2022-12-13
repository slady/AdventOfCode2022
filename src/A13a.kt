import Result.*
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.IntNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

enum class Result {
    RIGHT, NOT_RIGHT, SAME
}

object A13d {
    private val mapper = jacksonObjectMapper()

    @Throws(IOException::class)
    @JvmStatic
    fun main(a: Array<String>) {
        val lines = Files.readAllLines(Paths.get("a"))
        var s = 0

        for (i in 0..lines.size / 3) {
            if (nodesRight(mapper.readValue(lines[i * 3], JsonNode::class.java), mapper.readValue(lines[i * 3 + 1], JsonNode::class.java)) == RIGHT) {
                s += i + 1
            }
        }

        println(s)
    }

    private fun nodesRight(left: JsonNode?, right: JsonNode?): Result {
        if (left == null || right == null) {
            throw RuntimeException()
        }

        if (left is IntNode && right is IntNode)
            return if (left.intValue() < right.intValue()) return RIGHT else if (left.intValue() > right.intValue()) NOT_RIGHT else SAME;
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

            if (r == SAME)
                continue

            return r
        }

        if (right.size() > left.size())
            return RIGHT

        return SAME
    }
}
