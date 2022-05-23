package boj.doing

import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()
var N = 0
val q = LinkedList<Int>()

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
}

fun pro() {
    for(i in 1..N) {
        when(scan.next()) {
            "push" -> {
                val num = scan.nextInt()
                q.add(num)
            }
            "front" -> {
                if(!q.isEmpty()) {
                    sb.append(q.first()).append('\n')
                    //println(q.first())
                } else {
                    sb.append(-1).append('\n')
                    //println(-1)
                }
            }
            "back" -> {
                if(!q.isEmpty()) {
                    sb.append(q.last()).append('\n')
                    //println(q.last())
                } else {
                    sb.append(-1).append('\n')
                    //println(-1)
                }
            }
            "size" -> {
                sb.append(q.size).append('\n')
                //println(q.size)
            }
            "pop" -> {
                if(!q.isEmpty()) {
                    val top = q.pop()
                    sb.append(top).append('\n')
                    //println(top)
                } else
                    sb.append(-1).append('\n')
            }
            "empty" -> {
                if(q.isEmpty()) {
                    sb.append(1).append('\n')
                    //println(1)
                } else {
                    sb.append(0).append('\n')
                    //println(0)
                }
            }
        }
    }

    print(sb.toString())
}

class FastReader {
    var br: BufferedReader
    var st: StringTokenizer? = null

    constructor() {
        br = BufferedReader(InputStreamReader(System.`in`))
    }

    constructor(s: String?) {
        br = BufferedReader(FileReader(File(s)))
    }

    operator fun next(): String {
        while (st == null || !st!!.hasMoreElements()) {
            try {
                st = StringTokenizer(br.readLine())
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return st!!.nextToken()
    }

    fun nextInt(): Int {
        return next().toInt()
    }

    fun nextLong(): Long {
        return next().toLong()
    }

    fun nextDouble(): Double {
        return next().toDouble()
    }

    fun nextLine(): String {
        var str = ""
        try {
            str = br.readLine()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return str
    }
}
