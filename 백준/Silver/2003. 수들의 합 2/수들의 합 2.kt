import java.io.*
import java.util.*

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0
internal var S: Int = 0
internal var ans: Int = 0

lateinit var A: IntArray

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

fun solution(){
    var R = 0
    var sum = 0

    var cnt = 0

    for(L in 1..N) {
        // L - 1 을 구간에서 제외하기
        sum -= A[L-1]

        // R 을 옮길 수 있을 때까지 옮기기
        while(R + 1 <= N && sum < S){
            sum += A[++R]
        }

        //println("합: $sum")
        if(sum == S) {
            cnt++
            //break
        }
    }

    sb.append(cnt)
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