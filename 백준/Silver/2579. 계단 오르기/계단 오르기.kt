import java.io.*
import java.lang.Integer.max
import java.util.*

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0

lateinit var arr: IntArray
lateinit var dp: IntArray

fun main() {
    solve()
}

fun solve() {
    input()
    solution()
}

fun input() {
    N = scan.nextInt()
    // 굳이 타이트하게 N+1 로 잡을 필요 없다 index 에러난다 
    arr = IntArray(300 + 1)
    dp = IntArray(300 + 1)

    for (i in 1..N) {
        arr[i] = scan.nextInt()
    }
}

fun solution() {
    // 다수의 테스트 케이스를 처리하기 전에 모든 N에 대해 정답을 구해놓자.
    preprocess()

    sb.append(dp[N])
    print(sb.toString())
}

fun preprocess() {
    // dp[i]: i번째 계단까지 올라왔을때 총 점수의 최댓값
    // 한번에 한 계단 또는 두 계단씩 오를 수 있음
    // 마지막 계단은 반드시 밟아야함
    // 마지막 계단 -1번째를 밟는 경우, 마지막 계단 -2번째를 밟는 경우
    // 단, 마지막 계단 -1번째를 밟는 경우를 택할 경우 -2번째 계단은 밟으면 안됨
    // dp[n] = dp[n-3] + max(arr[n-1] , arr[n-2]) ??

    dp[1] = arr[1] //
    dp[2] = arr[1] + arr[2] // dp[1] + arr[2] -> ap[1] 보다 무조건 큼
    dp[3] = max(arr[1] + arr[3], arr[2] + arr[3]) // 예제 에서는 arr[2] + arr[3] 중요 지점

    for (i in 4..N) {
        dp[i] = arr[i] + max(dp[i - 2], dp[i - 3] + arr[i - 1])
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