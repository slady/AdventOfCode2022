package going

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object A07a {
    @Throws(IOException::class)
    @JvmStatic
    fun main(a: Array<String>) {
        val dirs = readStructure()
        println(countHigher(dirs))
        println(findSmallest(dirs))
    }

    private fun countHigher(dirs: ArrayList<Dir>): Int {
        return dirs.filter { d -> d.size <= 100000 }.sumOf(Dir::size)
    }

    private fun findSmallest(dirs: ArrayList<Dir>): Int {
        val limit = dirs[0].size - 40000000
        return dirs.filter { d -> d.size >= limit }.minOf(Dir::size)
    }

    @Throws(IOException::class)
    private fun readStructure(): ArrayList<Dir> {
        val dirs = ArrayList<Dir>()
        var active = Dir("/", null)
        dirs.add(active)

        for (line in Files.readAllLines(Paths.get("a.txt"))) {
            if (line.startsWith("$ cd ")) {
                val dirName = line.substring(5, line.length)
                active = when (dirName) {
                    "/" -> dirs[0]
                    ".." -> active.parent!!
                    else -> active.dirEntries.filterIsInstance<Dir>().first { d -> d.name == dirName }
                }
            } else if (line.startsWith("dir ")) {
                dirs.add(Dir(line.substring(4, line.length), active))
            } else if (line != "$ ls") { // just ignore this
                val s = line.split(' ')
                active.dirEntries.add(File(s[0].toInt(), s[1]))
            }
        }
        dirs[0].countSize()
        return dirs
    }
}

internal abstract class DirEntry protected constructor(val name: String) {
    abstract fun countSize(): Int
}

internal class Dir(name: String, val parent: Dir?) : DirEntry(name) {
    val dirEntries: MutableList<DirEntry> = ArrayList()

    var size = 0
        private set

    init {
        parent?.dirEntries?.add(this)
    }

    override fun countSize(): Int {
        size = dirEntries.sumOf(DirEntry::countSize)
        return size
    }
}

internal class File(private val size: Int, name: String) : DirEntry(name) {
    override fun countSize(): Int {
        return size
    }
}
