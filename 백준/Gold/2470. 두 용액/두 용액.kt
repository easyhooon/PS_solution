//도움받음
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
    solve()
}

fun solve() {
    input()

    //A에 대해 이분 탐색을 할 예정이니까, 정렬
    Arrays.sort(A, 1, N+1)

    best_sum = Int.MAX_VALUE

    for (left in 1..N) {
        // A[Left] 용액을 쓸 것이다. 고로 -A[left]와 가장 가까운 용액을 자신의 오른쪽 구간에서 찾자.
        val cand = lower_bound(A, left + 1, N, -A[left])

        // A[candidate -1] 와 A[candidate] 중에 A[left]와 섞었을 때의 정보를 정답에 갱신시킨다.

        //1. A[left] + A[candidate - 1]
        if(left + 1 <= cand - 1 && cand -1 <= N && abs(A[cand-1] + A[left]) < best_sum) {
            best_sum = abs(A[cand-1]+A[left])
            v1 = A[left]
            v2 = A[cand - 1]
        }

        //2. A[left] + A[candidate]
        if(left + 1 <= cand && cand <= N && abs(A[cand] + A[left]) < best_sum) {
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

    A = IntArray(N + 1)

    for (i in 1..N) {
        A[i] = scan.nextInt()
    }
}

fun lower_bound(A: IntArray, L: Int, R: Int, X: Int): Int {
    // A[L...R] 에서 X 이상의 수 중 제일 왼쪽 인덱스를 return 하는 함수
    // 만약 A[L...R] 중 X 이하의 수가 없다면 R + 1 을 return 한다.
    // res 의 초기값은 R + 1
    var L = L
    var R = R
    var res = R + 1

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