package boj.doing

import java.io.*
import java.util.*

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0
//dp[i][k] k = 0, 1 -> i번 와인을 시식했을때, 시식 하지 않았을때 최대 양
lateinit var dp: Array<IntArray>
lateinit var arr: IntArray

fun main() {
    solve()
}

fun solve() {
    input()
    solution()
}

fun input() {
    N = scan.nextInt()
    arr = IntArray(N+1)
    dp = Array(N+1) { IntArray(2) }

    for(i in 1..N) {
        arr[i] = scan.nextInt()
    }
}

fun solution() {
    preprocess()

    // 점화식을 토대로 Dy 배열 채우기
    // 가짜 문제를 정의하는데 성공했지만 점화식을 성공적으로 세우지 못함..
    for (i in 3..N) {
        //dp[i][0] = Math.max(dp[i - 2][0] + arr[i], dp[i - 2][1] + arr[i])
        dp[i][0] = (dp[i - 2][0] + arr[i]).coerceAtLeast(dp[i - 2][1] + arr[i])
        //dp[i][0] = Math.max(dp[i][0], Math.max(dp[i - 3][0], dp[i - 3][1]) + arr[i])
        dp[i][0] = dp[i][0].coerceAtLeast(dp[i - 3][0].coerceAtLeast(dp[i - 3][1]) + arr[i])

        dp[i][1] = dp[i - 1][0] + arr[i]
    }
    println(
//        Math.max(
//            Math.max(dp[N][0], dp[N][1]),
//            Math.max(dp[N - 1][0], dp[N - 1][1])
//        )
        dp[N][0].coerceAtLeast(dp[N][1]).coerceAtLeast(dp[N - 1][0].coerceAtLeast(dp[N - 1][1]))
    )
}

fun preprocess() {
    //초기값
    dp[1][0] = 0
    dp[1][1] = arr[1]

    if (N >= 2) {
        dp[2][0] = arr[2]
        dp[2][1] = dp[1][1] + arr[2]
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