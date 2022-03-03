package doing

import java.io.*
import java.util.*

//너무 어렵게 풀고 있나
internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0
internal var M: Int = 0
internal var ans: Int = 0
internal var cnt: Int = 0

lateinit var A: IntArray
//lateinit var B: ArrayList<Int>
//lateinit var C: ArrayList<Int>

fun main() {
    input()
    pro()
}

fun pro() {
    Arrays.sort(A, 1, N+1)

//    for(i in 1..N) {
//        B.add(A[i])
//    }

    val L = 1
    val R = N

//    for (i in 1..N) {
//        print("${A[i]} ")
//    }
//    println()

    solve(L, R)

    println(cnt/2)
    println(sb.toString())
}

fun input() {
    N = scan.nextInt()

    A = IntArray(N+1)
//    B = ArrayList<Int>()

    for(i in 1..N) {
        A[i] = scan.nextInt()
    }

    M = scan.nextInt()
}

fun solve(L: Int, R: Int) {
    for(i in 1..N) {
        var L = L
        var R = R
//        C = B
//        C.remove(A[i])

//        repeat(C.size) {
//            print("${C[i]} ")
//        }
//        println()


        while (L <= R) {
            val mid = ((L + R) / 2)
            if (determination(A[i], A[mid])) {
                ans = mid
                R = mid - 1
            } else {
                L = mid + 1
            }
        }
        if(A[ans] == M - A[i] ) {
            cnt++
//            println("$i $ans")
//            println("${A[i]} ${A[ans]}")
        }
    }
}

fun determination(element: Int, mid: Int): Boolean {
    return mid >= M - element
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