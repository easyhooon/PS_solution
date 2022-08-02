import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

var N = 0
lateinit var A: MutableSet<Int>

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    A = mutableSetOf<Int>()
    for(i in 1..N) {
        val element = scan.nextInt()
        A.add(element)
    }
    //set 을 정렬하고 mutableList 로 반환 
    val answer = A.sorted()
    for(e in answer) {
        print("$e ")
    }
}

fun pro() {

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