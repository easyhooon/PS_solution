import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

var N = 0
lateinit var A: ArrayList<Pair<Int, Int>>

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    A = ArrayList(N)
    for (i in 1..N) {
        val x = scan.nextInt()
        val y = scan.nextInt()
        A.add(Pair(x, y))
    }
}

fun pro() {
    val sortedA = A.sortedWith(compareBy({ it.first }, { it.second }))
    for(coord in sortedA) {
        println("${coord.first} ${coord.second}")
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