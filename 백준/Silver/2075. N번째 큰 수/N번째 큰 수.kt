import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()
//lateinit var arr: Array<IntArray>
val q = PriorityQueue<Int>(Collections.reverseOrder())

var N = 0

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
}

fun pro() {
    //arr = Array(N + 1) { IntArray(N + 1) }
    for (i in 0 until N) {
        for (j in 0 until N) {
            //arr[i][j] = scan.nextInt()
            val temp = scan.nextInt()
            q.add(temp)
        }
    }

//    for(i in 0 until N) {
//        for(j in 0 until N) {
//            q.add(arr[i][j])
//        }
//    }

    for(i in 0 until N-1) {
        q.poll()
    }
    sb.append(q.poll())
    print(sb)
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
