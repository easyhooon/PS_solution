import java.io.*
import java.util.*

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0

lateinit var A: IntArray

lateinit var dp: Array<Array<Int>> // i 일때 fibo(0)의 계산횟수 dp[i][0], i일때 fibo(1)의 계산횟수 dp[i][1]

internal var zero_cnt: Int = 0
internal var one_cnt: Int = 0

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

    A = IntArray(N + 1)

    for (i in 1..N) {
        A[i] = scan.nextInt()
    }

    dp = Array(45) { Array(2) { 0 } }
}

fun solution() {
    dp[0][0] = 1
    dp[0][1] = 0

    dp[1][0] = 0
    dp[1][1] = 1


    /*

    fibo(2) = fibo(1) + fibo(0)

    dp[2][0] = dp[1][0] + dp[0][0]
    dp[2][1] = dp[1][1] + dp[0][1]
     */

//    for(i in 1..N) {
//        fibonacci(A[i])
//        sb.append(zero_cnt).append(" ").append(one_cnt)
//        println(sb.toString())
//        zero_cnt = 0
//        one_cnt = 0
//        sb.clear()
//    }
    for (i in 2..40) {
        dp[i][0] = dp[i - 1][0] + dp[i - 2][0]
        dp[i][1] = dp[i - 1][1] + dp[i - 2][1]
    }

    for (i in 1..N) {
        sb.append(dp[A[i]][0]).append(" ").append(dp[A[i]][1])
        println(sb.toString())
        sb.clear()
    }
}

fun fibonacci(n: Int): Int {
    if (n == 0) {
        zero_cnt++
        return 0
    } else if (n == 1) {
        one_cnt++
        return 1
    } else {
        return fibonacci(n - 1) + fibonacci(n - 2)
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