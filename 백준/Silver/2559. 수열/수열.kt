package doing

import java.io.*
import java.lang.Integer.max
import java.util.*

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0
internal var S: Int = 0

lateinit var A: IntArray

//주어진 수가 모두 음수일 때
fun main() {
    solve()
}

fun solve() {
    input()
    solution()
}

fun input() {
    N = scan.nextInt()
    S = scan.nextInt()

    A = IntArray(N + 1)

    for (i in 1..N) {
        A[i] = scan.nextInt()
    }
}

fun solution() {
    //N은 2이상 100,000이하
    //K는 1과 N사이
    var R = 0
    var sum = 0
    // 수열에 음수가 포함될 경우 max_sum을 신중하게 설정해야 한다.
    var max_sum = Integer.MIN_VALUE

    //R이 MAX값이 되어도 N이 끝까지 돌아서 문제 발생
    for(L in 1..N) {
        // L - 1 을 구간에서 제외하기
        sum -= A[L-1]

        // R 을 옮길 수 있을 때까지 옮기기
        while(R + 1 <= N && (R - L + 1) < S ){
            sum += A[++R]
        }
        max_sum = max(max_sum, sum)
//        println("$R $max_sum")
        if(R == N) {
            break
        }
    }

    sb.append(max_sum)
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