package doing

import java.io.*
import java.lang.Integer.max
import java.lang.Integer.min
import java.util.*

// 답 참조
// 블루레이 길이를 mid로 했을때 m개로 쪼갤 수 있을까?
internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0
internal var M: Int = 0
internal var sum: Int = 0
internal var ans: Int = 0

lateinit var A: IntArray

fun main() {
    solve()
}

fun solve() {
    input()

//    val L = 1
    val L = A[1]
    val R = 1000000000

    //Arrays.sort(A, 1, N + 1)

//    for (i in 1..N) {
//        print("${A[i]} ")
//    }
//    println()

    ans = solve(L, R)

    sb.append(ans)
    println(sb.toString())
}

fun input() {
    N = scan.nextInt()
    M = scan.nextInt()

    A = IntArray(N + 1)

    for (i in 1..N) {
        A[i] = scan.nextInt()
    }
}

fun solve(L: Int, R: Int): Int {
    var L = L
    var R = R
    // 적어도 제일 긴 녹화분의 길이 만큼이 필요하다!
    for(i in 1..N) {
        L = max(L, A[i])
    }

    while (L <= R) {
        val mid = ((L + R) / 2)
        //println("mid: $mid")
        if (determination(mid)) {
            ans = mid
            R = mid - 1
        } else {
            L = mid + 1
        }
    }
    return ans
}

fun determination(mid: Int): Boolean {
    var cnt = 1
    sum = 0
    for(i in 1..N) {
        if(sum + A[i] > mid) {
            cnt++
            sum = A[i]
        } else {
            sum += A[i]
        }
    }
    return cnt <= M
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