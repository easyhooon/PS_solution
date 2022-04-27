import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

var N: Int = 0

lateinit var num: Array<Int>
lateinit var con: Array<ArrayList<Int>>
lateinit var dy: Array<Array<Int>>

fun main() {
    solve()
}

fun solve() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()

    num = Array(N + 1) { 0 }
    con = Array(N + 1) { ArrayList<Int>() }

    for (i in 1..N) {
        num[i] = scan.nextInt()
        con[i] = ArrayList()
    }

    for (i in 1 until N) {
        val x = scan.nextInt()
        val y = scan.nextInt()
        con[x].add(y)
        con[y].add(x)
    }
}

fun dfs(x: Int, prev: Int) {
    dy[x][1] = num[x]
    for (y in con[x]) {
        if (y == prev)
            continue
        dfs(y, x)
        dy[x][0] += Math.max(dy[y][0], dy[y][1])
        dy[x][1] += dy[y][0]
    }
}

fun pro() {
    dy = Array(N + 1) { Array(2) { 0 } }

    dfs(1, -1)

    sb.append(Math.max(dy[1][0], dy[1][1]))
    println(sb)
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
