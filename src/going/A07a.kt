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

    private fun countHigher(dirs: ArrayList<Dir?>): Int {
        var sum = 0
        for (dir in dirs) {
            val size = dir!!.countSize()
            if (size <= 100000) {
                sum += size
            }
        }
        return sum
    }

    private fun findSmallest(dirs: ArrayList<Dir?>): Int {
        var max = dirs[0]!!.countSize()
        val limit = max - 40000000
        for (dir in dirs) {
            val size = dir!!.countSize()
            if (size in limit..max) {
                max = size
            }
        }
        return max
    }

    @Throws(IOException::class)
    private fun readStructure(): ArrayList<Dir?> {
        val lines = Files.readAllLines(Paths.get("a.txt"))
        val dirs = ArrayList<Dir?>()
        var active: Dir? = Dir("/", null)
        dirs.add(active)

        for (line in lines) {
            if (line.startsWith("$ cd ")) {
                val dirName = line.substring(5, line.length)
                if (dirName == "/") {
                    active = dirs[0]
                } else if (dirName == "..") {
                    active = active!!.parent
                } else {
                    for (dirEntry in active!!.dirEntries) {
                        if (dirEntry is Dir && dirEntry.name == dirName) {
                            active = dirEntry as Dir
                        }
                    }
                }
            } else if (line == "$ ls") {
                // just ignore this
            } else if (line.startsWith("dir ")) {
                val dir = Dir(line.substring(4, line.length), active)
                active!!.add(dir)
                dirs.add(dir)
            } else {
                val s = line.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                active!!.add(File(s[1], s[0].toInt()))
            }
        }
        return dirs
    }
}

internal abstract class DirEntry protected constructor(val name: String) {
    abstract fun countSize(): Int
}

internal class Dir(name: String, val parent: Dir?) : DirEntry(name) {
    val dirEntries: MutableList<DirEntry> = ArrayList()
    fun add(dirEntry: DirEntry) {
        dirEntries.add(dirEntry)
    }

    override fun countSize(): Int {
        return dirEntries.sumOf(DirEntry::countSize)
    }
}

internal class File(name: String, private val size: Int) : DirEntry(name) {
    override fun countSize(): Int {
        return size
    }
}
