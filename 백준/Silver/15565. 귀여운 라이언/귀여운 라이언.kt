package boj.doing

import java.io.*
import java.lang.Integer.min
import java.util.*

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0
internal var K: Int = 0

lateinit var A: IntArray

//문제를 제대로 읽어라... 그런 집합이 없다면 -1 을 출력하라...
fun main() {
    solve()
}

fun solve() {
    input()
    solution()
}

fun input() {
    N = scan.nextInt()
    K = scan.nextInt()

    A = IntArray(N + 1)

    for (i in 1..N) {
        A[i] = scan.nextInt()
    }
}

fun solution() {
    //N은 2이상 100,000이하
    //K는 1과 N사이
    var R = 0
    var cnt = 0
    // 수열에 음수가 포함될 경우 max_sum을 신중하게 설정해야 한다.
    var min_length = Integer.MAX_VALUE

    //R이 MAX값이 되어도 N이 끝까지 돌아서 문제 발생, 또 발생
    //L은 k의 개수를 마지막으로 만족한 시점에서 종료되어야 한다.
    for (L in 1..N) {
        // L - 1 을 구간에서 제외하기
        if (A[L - 1] == 1) {
            cnt--
        }

        // R 을 옮길 수 있을 때까지 옮기기
        while (R + 1 <= N) {
            if (cnt == K) {
                break
            } else {
                if (A[++R] == 1) {
                    cnt++
                }
            }
        }

        if (cnt == K) {
            //cnt가 K일때만 갱신되도록
            min_length = min(min_length, R - L + 1)
            //println(min_length)
        }
    }

    if(min_length == Integer.MAX_VALUE) {
        sb.append(-1)
        println(sb.toString())
    }
    else {
        sb.append(min_length)
        println(sb.toString())
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