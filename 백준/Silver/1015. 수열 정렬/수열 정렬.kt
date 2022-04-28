import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

var N: Int = 0
lateinit var num: IntArray
lateinit var arr: IntArray
lateinit var ans: IntArray
lateinit var check: BooleanArray

fun main() {
    solve()
}

fun solve() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    //원래 배열
    num = IntArray(N)
    //정렬된 배열
    arr = IntArray(N)
    //정렬된 배열 check 배열
    check = BooleanArray(N)
    //정답 배열
    ans = IntArray(N) {-1}
    
    for (i in 0 until N) {
        num[i] = scan.nextInt()
    }
}

fun pro() {
    arr = num.sortedArray()

    for (i in 0 until N) {
        Break@for (j in 0 until N) {
            if (num[i] == arr[j] && ans[i] == -1 && !check[j]) {
                ans[i] = j
                check[j] = true
                break@Break
            }
        }
    }

    for (i in ans) {
        print("$i ")
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
