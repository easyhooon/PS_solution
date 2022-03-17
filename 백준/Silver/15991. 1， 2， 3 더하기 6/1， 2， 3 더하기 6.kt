import java.io.*
import java.util.*

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var tc: Int = 0
internal var MOD: Int = 1000000009
internal var MAX: Int = 100000

lateinit var arr: IntArray
lateinit var pre_dp: IntArray
lateinit var dp: IntArray

// 합이 대칭을 이뤄야 한다
// 님아 문제 풀다가 문제 의도에 벗어났어요 1 2 3으로만 표현할 수 있어여
fun main() {
    solve()
}

fun solve() {
    input()
    solution()
}

fun input() {
    tc = scan.nextInt()
    arr = IntArray(tc + 1)
    dp = IntArray(MAX + 5)
    pre_dp = IntArray(MAX + 5)

    for (i in 1..tc) {
        arr[i] = scan.nextInt()
    }
}

fun solution() {
    // 다수의 테스트 케이스를 처리하기 전에 모든 N에 대해 정답을 구해놓자.
    preprocess()

    for (i in 1..tc) {
        sb.append(dp[arr[i]]).append('\n')
    }
    print(sb.toString())
}

fun preprocess() {
    prepreprocess()

    //dp[i] : i 를 1과 2와 3의 합으로 나타내는 경우의 수
    dp[1] = 1 // 1
    dp[2] = 2 // 1+1, 2
    dp[3] = 2 // 1+1+1, 3

    for (i in 4..MAX) {
        // 짝수
//        if (i % 2 == 0) {
//            // 짝수개, 홀수개
//            dp[i] = pre_dp[i / 2] + pre_dp[i / 2 - 1]
//        }
//        //홀 수
//        else {
//            //가운데가 1 , 가운데가 3
//            dp[i] = pre_dp[i / 2] + pre_dp [i / 2 - 1]
//        }
        dp[i] = (pre_dp[i / 2] + pre_dp[i / 2 - 1]) % MOD
    }
}

fun prepreprocess() {
    //dp[i] : i 를 1과 2와 3의 합으로 나타내는 경우의 수
    pre_dp[1] = 1 // 1
    pre_dp[2] = 2 // 1+1, 2
    pre_dp[3] = 4 // 1+1+1, 1+2, 2+1, 3

    for (i in 4..MAX) {
        //괄호...
        // 식 작성의 문제 이미 세개를 더할때 Int 제한을 초과할 수 있다.
        // 아예 long long으로 바꾸던지
        // dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % MOD
        // 덧셈을 수행할때마다 모듈러 계산을 수행하던지
        pre_dp[i] = ((pre_dp[i - 1] + pre_dp[i - 2]) % MOD + pre_dp[i - 3]) % MOD
        // 마지막 자리가 1로 끝나는 경우 + 2로 끝나는 경우 + 3으로 끝나는 경우
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