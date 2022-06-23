import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

var N = 0
var str = ""
lateinit var dp: IntArray
const val MOD = 1000000

fun main() {
    input()
    pro()
}

fun input() {
    str = scan.nextLine()
    N = str.length

}

fun pro() {
    dp = IntArray(N)
    
    // 초기값 구하기
    if (str[0] != '0')
        dp[0] = 1

    // 점화식을 토대로 Dy 배열 채우기
    for (i in 1 until N) {
        // i 번 숫자를 단독으로 해석 가능할 때
        if (str[i] != '0') dp[i] = dp[i - 1]

        // i - 1번과 i 번 숫자를 하나의 문자로 해석 가능할 때
        if (check(str[i - 1], str[i])) {
            if (i >= 2) dp[i] += dp[i - 2]
            else dp[i] += 1

            dp[i] %= MOD
        }
    }

    // Dp 배열로 정답 계산하기
    println(dp[N - 1])
}

// 'AB' 라는 두 자리 숫자가 하나의 수로 해독이 가능한가?
fun check(A: Char, B: Char): Boolean {
    if (A == '0') return false
    if (A == '1') return true
    if (A >= '3') return false
    return B <= '6'
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