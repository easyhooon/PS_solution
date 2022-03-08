package boj.doing

import java.io.*
import java.util.*

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0
internal var M: Int = 0
internal var ans: Int = 0

lateinit var A: IntArray
lateinit var B: IntArray

fun main() {
    solve()
}

fun solve() {
    input()

    val L = 1
    val R = A.size - 1

    //0번 인덱스를 비워둔채 정렬을 하면 0이 존재하게 된다.
    //는 인덱스 1, N+1이라고 범위를 지정했는데
    //ㅇㅇ 문제없음

    Arrays.sort(A, 1, N + 1)
    //B는 정렬할 필요X
    //Arrays.sort(B, 1, M + 1)

    //println(A.contentToString())
    //println(B.contentToString())

    for(i in 1..M) {
        val ans = solve(B[i], L, R)

        //println("ans: $ans")

        if(B[i] == ans) {
            sb.append(1).append(" ")
        }
        else {
            sb.append(0).append(" ")
        }
    }

    println(sb.toString())
}

fun input() {
    N = scan.nextInt()

    //초기화가 자동으로 이루어짐
    A = IntArray(N + 1)

    for (i in 1..N) {
        A[i] = scan.nextInt()
    }

    M = scan.nextInt()

    B = IntArray(M + 1)

    for (i in 1..M) {
        B[i] = scan.nextInt()
    }
}

//같은 숫자를 비교하고 끝내는 걸 어떻게 표현하지
//while 문의 종료조건에 의해 같아진 경우 또는 최종 선택된 값을 return 할 수 있다.
fun solve(i: Int, L: Int, R: Int): Int {
    var L = L
    var R = R

    while (L <= R) {
        val mid = ((L + R) / 2)
        //println("L: $L R: $R")
        if (determination(i, mid)) {
            //println(mid)
            ans = A[mid]
            L = mid + 1

        } else {
            R = mid - 1
        }
    }
    //println("ans: $ans")
    return ans
}

fun determination(i: Int, mid: Int): Boolean {
    return i >= A[mid]
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