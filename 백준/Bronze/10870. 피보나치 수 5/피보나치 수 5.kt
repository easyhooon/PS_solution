package boj.doing

import java.io.*
import java.util.*

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0

lateinit var dp: Array<Int> // i 일때 fibo(0)의 계산횟수 dp[i][0], i일때 fibo(1)의 계산횟수 dp[i][1]

//동적 계획법은 분할정복과 다르게 메모이제이션이 필수!
fun main() {
    solve()
}

fun solve() {
    input()
    solution()
}

fun input() {
    N = scan.nextInt()

    dp = Array(25){ 0 }
}

fun solution() {
    dp[0] = 0
    dp[1] = 1

    for (i in 2..20) {
        dp[i] = dp[i - 1] + dp[i - 2]
    }

    sb.append(dp[N])
    println(sb.toString())
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