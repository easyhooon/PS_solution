// lower_bound 와 upper_bound 의 정의.. 도움
// ans 갱신 위치, ans 초기값 확인

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
    solution()
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

fun solution() {
    val L = 1
    //val R = A.size - 1
    val R = N

    //println(R)

    // 0번 인덱스를 비워둔채 정렬을 하면 0이 존재하게 된다.
    // 는 인덱스 1, N+1이라고 범위를 지정했는데
    // ㅇㅇ 문제없음

    Arrays.sort(A, 1, N + 1)

    for(i in 1..M) {
        //solve -> cnt 를 return
        val upper_index = upper_bound(B[i], L, R)
        val lower_index = lower_bound(B[i], L, R)

        sb.append(upper_index - lower_index).append(' ')
    }

    println(sb.toString())
}

// 찾고자 하는 값 이상이 처음 나타나는 위치
fun upper_bound(i: Int, L: Int, R: Int): Int {
    var L = L
    var R = R
    var ans = R+1

    while (L <= R) {
        val mid = ((L + R) / 2)
        if (i >= A[mid]) {
            L = mid + 1
        } else {
            ans = mid
            R = mid - 1
        }
    }
    return ans
}

// 찾고자 하는 값보다 큰 값이 처음으로 나오는 위치
fun lower_bound(i: Int, L:Int, R: Int): Int {
    var L = L
    var R = R
    var ans = R+1

    while (L <= R) {
        val mid = ((L + R) / 2)
        if (i > A[mid]) {
            L = mid + 1
        } else {
            R = mid - 1
            ans = mid
        }
    }
    return ans
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