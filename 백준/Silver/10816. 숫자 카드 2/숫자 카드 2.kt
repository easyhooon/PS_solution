package boj.doing

// lower_bound 와 upper_bound 의 정의.. 도움
// ans 초기값, ans 갱신 위치
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

// 정렬되어있는 데이터 집합에서 k값 초과하는 값이 처음 발견되는 위치
// 정의에 맞게(해석과 동일하게) 식 작성
// R 초기값 
fun upper_bound(i: Int, L: Int, R: Int): Int {
    var L = L
    var R = R
    var ans = R+1 // ans 가 한번도 갱신이 되지 않았다. 선택된 수가 A의 모든 수보다 크다 -> R+1

    while (L <= R) {
        val mid = ((L + R) / 2)
        if (A[mid] > i) {
            ans = mid;
            R = mid - 1;
        } else {
            L = mid + 1;
        }
    }
    return ans
}

// 정렬되어있는 데이터 집합에서 k값 이상이 처음 발견되는 위치
fun lower_bound(i: Int, L:Int, R: Int): Int {
    var L = L
    var R = R
    var ans = R+1

    while (L <= R) {
        val mid = ((L + R) / 2)
        if (A[mid] >= i) {
            ans = mid;
            R = mid - 1;
        } else {
            L = mid + 1;
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