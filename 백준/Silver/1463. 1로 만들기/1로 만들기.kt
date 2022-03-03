import java.io.*
import java.lang.Integer.min
import java.util.*

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0
internal var ans: Int = 0
lateinit var dp: IntArray


fun main() {
   solve()
}

fun solve() {
    input()
    solution()
}

fun solution() {
    dp[1] = 0
    dp[2] = 1
    dp[3] = 1

    //모두 if로 처리해야 3가지의 연산을 모두 수행한다.
    for(i in 4..N) {
        dp[i] = dp[i-1] + 1
        if (i % 3 == 0) {
            dp[i] = min(dp[i], dp[i / 3] + 1)
        }
        if (i % 2 == 0) {
            dp[i] = min(dp[i], dp[i / 2] + 1)
        }
    }

//    for(i in 1..N) {
//        print("$i : ${dp[i]} ")
//        println()
//    }

    sb.append(dp[N])
    print(sb.toString())
}

fun input() {
    N = scan.nextInt()
    dp = IntArray(1000001)
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