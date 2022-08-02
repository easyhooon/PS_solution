import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

var N = 0
lateinit var A: IntArray
lateinit var B: IntArray
//lateinit var copyOfB: IntArray

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    A = IntArray(N)
    B = IntArray(N)
    //copyOfB = IntArray(N)

    for(i in 0 until N) {
        A[i] = scan.nextInt()
    }
    for(i in 0 until N) {
        B[i] = scan.nextInt()
    }
    //copyOfB = B.copyOf()
    //copyOfB.sort()
}

fun pro() {
    A.sort()
    B.sortDescending()

    var sum = 0
    for(i in 0 until N) {
        sum += A[i]*B[i]
    }
    println(sum)
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