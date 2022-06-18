import java.io.*
import java.util.*

var scan = FastReader()
var sb = StringBuilder()
var N = 0
lateinit var A: IntArray

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    A = IntArray(N + 1)
    for (i in 1..N) {
        A[i] = scan.nextInt()
    }
}

fun pro() {
    // 최소, 최대 원소를 빠르게 찾기 위해서 정렬을 미리 해주자.
    Arrays.sort(A, 1, N + 1)

    var best_sum = Int.MAX_VALUE
    var v1 = 0
    var v2 = 0
    var L = 1
    var R = N
    while (L < R) {  // L == R 인 상황이면 용액이 한 개 뿐인 것이므로, L < R 일 때까지만 반복한다.
        if (best_sum > Math.abs(A[L] + A[R])) {
            best_sum = Math.abs(A[L] + A[R])
            v1 = A[L]
            v2 = A[R]
        }
        if (A[L] + A[R] > 0) R-- else L++
    }
    sb.append(v1).append(' ').append(v2)
    println(sb)
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
