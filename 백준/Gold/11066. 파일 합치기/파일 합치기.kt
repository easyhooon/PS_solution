import java.io.*
import java.util.*

internal val scan = FastReader()
internal val sb = StringBuilder()

var tc: Int = 0
var K: Int = 0

//Dy[i][j]:= i번 ~ j번 파일을 하나로 합치는 최소 비용 (시작을 1번으로 하면 점화식이 세워지지 않음, 시작점의 대한 정보가 있어야 함)
//
//Sum[i][j] := i번 ~ j번 파일의 총 크기

lateinit var num: Array<Int>
lateinit var sum: Array<Array<Int>>
lateinit var dy: Array<Array<Int>>

fun main() {
    solve()
}

fun solve() {
    tc = scan.nextInt()
    for(i in 1..tc) {
        input()
        pro()
    }
}

fun input() {
    K = scan.nextInt()
    num = Array(K+1) { 0 }
    sum = Array(K+1) { Array(K+1){ 0 } }

    for(i in 1..K) {
        num[i] = scan.nextInt()
    }
}

fun pro() {
    preprocess()

    dy = Array(K+1) { Array(K+1) { 0 } }

    for (len in 2..K) {
        for (i in 1..K-len+1) {
            val j = i + len -1
            dy[i][j] = Int.MAX_VALUE
            for(k in i until j) {
                dy[i][j] = Math.min(dy[i][j], dy[i][k] + dy[k+1][j] + sum[i][j])
            }
        }
    }

    println(dy[1][K])
}

fun preprocess() {
    for (i in 1..K) {
        for (j in i..K) {
            sum[i][j] = sum[i][j-1] + num[j]
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