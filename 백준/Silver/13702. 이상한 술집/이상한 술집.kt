package boj.doing

import java.io.*
import java.util.*

// R이 Int 범위라고 Int 로 풀면안된다!!!
//  막걸리의 용량은 자연수 또는 0이다...입력값에서 코너케이스를 확인해라
//  반례
//  1 1
//  0
//  ans : 0
//
//  1 1
//  1
//  ans : 1
//
//  1 1
//  2100000000
//  ans : 2100000000
// -> R이 21이고 mid 가 10.5억이면 둘이 더하는 과정에서(2로 나누기전에) 오버플로우 발생
val scan = FastReader()
val sb = StringBuilder()

var N = 0
var M = 0
var ans: Long = 0

lateinit var capacity: LongArray

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    M = scan.nextInt()
    capacity = LongArray(N)
    for(i in 0 until N) {
        capacity[i] = scan.nextLong()
    }
}

fun pro() {
    val L: Long = 1
    val R: Long = capacity.maxOrNull()!!

    ans = binary_search(L, R)
    sb.append(ans)
    print(sb)
}

fun binary_search(L: Long, R: Long): Long {
    var L = L
    var R = R

    //ans = Int.MAX_VALUE 이거 왜 남겨놨지..? 복붙 풀이의 피해

    while (L <= R) {
        val mid = ((L + R) / 2)
        //println(mid)
        if (determination(mid)) {
            ans = mid
            L = mid + 1
        } else {
            R = mid - 1
        }
    }
    return ans
}

fun determination(mid: Long): Boolean {
    var cnt: Long = 0
    val divisor = mid

    for (i in 0 until N) {
        if(divisor <= capacity[i]) {
            val dividend = capacity[i] / divisor
            cnt += dividend
        }
    }

    return cnt >= M
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
