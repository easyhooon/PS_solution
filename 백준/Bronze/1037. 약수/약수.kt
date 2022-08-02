import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

var N = 0
lateinit var A: IntArray

//정렬을 먼저 해주어야겠다 예제를 봐라

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    A = IntArray(N)
    for (i in 0 until N) {
        A[i] = scan.nextInt()
    }
}

fun pro() {
    A.sort()

    if (N % 2 == 0) {
        println(A[0] * A[N - 1])
    } else {
        //약수의 개수가 홀수개는 제곱수가 유일하다 다만 36과 같은 수도 존재한다 (약수의 개수가 3개가 아닌)
        if (N == 1) {
            println(A[0] * A[0])
        } else {
            val mid = (N / 2)
            // ㅋㅋ 배열인덱스 -1 ㅠㅠ
            // 아니 배열엔 1과 자기 자신이 없어요...
            // 제곱 해야죠...
            println(A[mid] * A[mid])
        }
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