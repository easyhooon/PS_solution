import java.io.*
import java.util.*

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var A: Int = 0
internal var B: Int = 0
internal var V: Int = 0


fun main() {
    solve()
}

fun solve() {
    input()
    solution()
}

fun input() {
    A = scan.nextInt()
    B = scan.nextInt()
    V = scan.nextInt()
}

//fun solution() {
//    var cnt = 1
//    var sum = 0
//    while(true) {
//        sum += A
//        if(V > sum) {
//            sum -= B
//            cnt++
//        }
//        else {
//            break
//        }
//    }
//
//    sb.append(cnt)
//    print(sb.toString())
//}

fun solution() {
    var ans = (V - B) / (A - B)

    if ((V - B) % (A - B) != 0) {
        ans += 1
    }

    sb.append(ans)
    println(sb.toString())
}


//fun lower_bound(L: Int, R: Int, X: Int): Int {
//    // A[L...R] 에서 X 미만의 수 중 제일 오른쪽 인덱스를 return 하는 함수
//    // 그런 게 없다면 L - 1 을 return 한다
//    var L = L
//    var R = R
//    var mid = 0
//
//    while (L <= R) {
//        mid = (L + R) / 2
//        if (determination(mid)) {
//            // mid 아래는 어차피 다 x보다 작음 -> L 을 땡겨와야 함
//            L = mid + 1
//        } else {
//            // mid 보다 큰 건 어차피 다 x보다 큼 -> R 을 땡겨와야 함
//            R = mid - 1
//        }
//    }
//
//    return mid
//}

//fun determination(mid: Int): Boolean {
//    val sum = A * mid - B * (mid - 1)
//
//    return V > sum
//}


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