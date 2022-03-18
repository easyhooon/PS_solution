import java.io.*
import java.util.*

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0
internal var B: Int = 0
internal var C: Int = 0

//최대 값 계산 고려해야지..문제풀기전에 아무리 브론즈라도 ...
lateinit var arr: Array<Int>

fun main() {
    solve()
}

fun solve() {
    input()
    solution()
}

fun input() {
    N = scan.nextInt()
    arr = Array<Int>(N+1) { 0 }
    for(i in 1..N) {
        arr[i] = scan.nextInt()
    }
    B = scan.nextInt()
    C = scan.nextInt()
}

fun solution() {

    var cnt = 0L

    for(i in 1..N) {
        arr[i] -= B
        cnt++
    }

    for(i in 1..N) {
        if(arr[i] > 0) {
            if(arr[i] % C == 0) {
                cnt += (arr[i] / C).toLong()
            }
            else {
                cnt += (arr[i] / C + 1).toLong()
            }
        }
    }

    sb.append(cnt)
    print(sb.toString())
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