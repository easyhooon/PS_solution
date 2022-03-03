package doing

import java.io.*
import java.util.*
import kotlin.math.abs

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0
internal var best_sum: Int = 0
internal var v1: Int = 0
internal var v2: Int = 0

lateinit var A: IntArray

fun main() {
//    val tc = scan.nextInt()
//
//    for(i in 1..tc) {
//        solve()
//    }
    solve()
}

fun solve() {
    input()

    Arrays.sort(A, 0, N)

//    for (i in 0 until N) {
//        print("${A[i]} ")
//    }
//    println()

    best_sum = Int.MAX_VALUE

    for (left in 0 until N) {
        val cand = lower_bound(A, left + 1, N - 1, -A[left])

        // A[cand - 1] 와 A[cand] 중에 A[left] 와 섞었을 때의 정보를 정답에 갱신시킨다.
//        if (abs(A[cand] + A[left]) < abs(A[cand - 1] + A[left])) {
//            v1 = A[left]
//            v2 = A[cand]
//        } else {
//            v1 = A[left]
//            v2 = A[cand - 1]
//        }

        if(left + 1 <= cand - 1 && cand -1 <= N-1 && abs(A[cand-1] + A[left]) < best_sum) {
            best_sum = abs(A[cand-1]+A[left])
            v1 = A[left]
            v2 = A[cand - 1]
        }

        if(left + 1 <= cand && cand <= N-1 && abs(A[cand] + A[left]) < best_sum) {
            best_sum = abs(A[cand]+A[left])
            v1 = A[left]
            v2 = A[cand]
        }
    }

    sb.append(v1).append(' ').append(v2)
    print(sb.toString())
}

fun input() {
    N = scan.nextInt()

    // 0 이 초기값으로 들어가는 관계로 0 ~ N-1로 range 를 잡아야
    A = IntArray(N + 1)

    for (i in 0 until N) {
        A[i] = scan.nextInt()
    }

}

// 직접 배열을 손으로 써보며 이해
//fun lower_bound(A: IntArray, L: Int, R: Int, X: Int): Int {
//    // A[L...R] 에서 X 미만의 수 중 제일 오른쪽 인덱스를 return 하는 함수
//    // 그런 게 없다면 L - 1 을 return 한다
//    var L = L
//    var R = R
//    var res = L - 1 // 만약 A[L...R] 중 X 이하의 수가 없다면 L - 1 을 return 한다.
//    while (L <= R) {
//        val mid = (L + R) / 2
//        if (A[mid] < X) {
//            // mid 아래는 어차피 다 x보다 작음 -> L 을 땡겨와야 함
//            res = mid
//            L = mid + 1
//        } else {
//            // mid 보다 큰 건 어차피 다 x보다 큼 -> R 을 땡겨와야 함
//            R = mid - 1
//        }
//    }
//    return res
//}

fun lower_bound(A: IntArray, L: Int, R: Int, X: Int): Int {
    // A[L...R] 에서 X 이상의 수 중 제일 왼쪽 인덱스를 return 하는 함수
    // 그런 게 없다면 L - 1 을 return 한다
    var L = L
    var R = R
    var res = R + 1 // 만약 A[L...R] 중 X 이하의 수가 없다면 R + 1 을 return 한다.
    while (L <= R) {
        val mid = (L + R) / 2
        if (A[mid] >= X) {
            res = mid
            R = mid - 1
        } else {
            L = mid + 1
        }
    }
    return res
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