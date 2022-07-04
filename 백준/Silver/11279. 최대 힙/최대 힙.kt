import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

val q = PriorityQueue<Int>(Collections.reverseOrder())

var N = 0

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
}

fun pro() {
    for (i in 0 until N) {
        val input = scan.nextInt()

        if(input == 0) {
            if(!q.isEmpty()) {
                println(q.poll())
            }
            else {
                println(0)
            }
        }
        else {
            q.add(input)
        }
    }
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
