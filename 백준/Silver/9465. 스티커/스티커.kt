import java.io.*
import java.util.*
import kotlin.math.max

val scan = FastReader()
val sb = StringBuilder()

var tc = 0
var N = 0

lateinit var arr: Array<IntArray>
//n번째 열에 i행을 골랐을때 최댓값
lateinit var dp: Array<IntArray>

fun main() {
    input()
    pro()
}

fun input() {
    tc = scan.nextInt()
}

fun pro() {
    for (i in 1..tc) {
        N = scan.nextInt()
        arr = Array(3) { IntArray(N+1) }
        dp = Array(3) {IntArray(N+1) }
        for (j in 1..2) {
            for (k in 1..N) {
                arr[j][k] = scan.nextInt()
            }
        }

//        for(i in arr.indices) {
//            println(arr[i].contentToString())
//        }

        // base
        // 첫번째를 1행을 선택했을때 1행까지의 합
        dp[1][1] = arr[1][1]
        // 첫번째를 2행을 선택했을때 2행까지의 합
        dp[2][1] = arr[2][1]

        //두번째 부터도 아래 점화식을 적용해줘야한다.
//        dp[1][2] = dp[1][1] + dp[2][2]
//        dp[2][2] = dp[2][1] + dp[1][2]

        // 조건 바로 전 열에서 선택한 행은 선택할 수 없음

        // 한 열을 아예 안 골를수 도 있음 다음 열에 더 큰 값을 선택하기 위해해

        for(i in 2..N) {
            //1. 이전 칸에 고른 것이 다음 칸에 영향을 주지 않는 경우
            //2. 이전 칸에 고른 것이 다음 칸에 영향을 주는 경우
            //둘 중에 값이 큰 것이 해당 열까지의 최댓값


            //1. 그냥 대각선으로 합을 구하는 경우 (예제의 50->50->100)
            //2. 두 칸 뒤 대각선으로 이동하는 경우의 수 (예제의 100 -> 60)
            //음 전에 골랐던 행을 어떻게 알지...
            dp[1][i] = max(dp[2][i-1], dp[2][i-2]) + arr[1][i]
            dp[2][i] = max(dp[1][i-1], dp[1][i-2]) + arr[2][i]
        }
        sb.append(max(dp[1][N], dp[2][N])).append('\n')
    }
    print(sb)
    //print(sb.toString()) 뒤에 toString() 안해줘도 된다.
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
