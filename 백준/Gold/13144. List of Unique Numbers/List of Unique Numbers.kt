import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

// 도움...
// 연속된 1개 이상의 수를 뽑았을때 -> 투포인터 힌트
var N = 0
var sum: Long = 0 // 최대 범위 Int 초과.......
lateinit var arr: IntArray
var count: IntArray = IntArray(100001)
var max_value = 0

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    arr = IntArray(N+1)
    for (i in 1..N) {
        arr[i] = scan.nextInt()
        max_value = Math.max(max_value, arr[i])
    }
}

fun pro() {
    var R = 0

    for(L in 1..N) {
        // L - 1 을 구간에서 제외하기
        // 1로 체크했던거 체크 해제
        count[arr[L - 1]] = 0

        // R 을 옮길 수 있을 때까지 옮기기
        // 끝까지 도달하지 않았고, 겹치는 수가 없으면
        // 겹치는 수가 없는지 판단하는 방법
        // counting sort
        while(R + 1 <= N && count[arr[R+1]] == 0) {
            count[arr[++R]] = 1
        }

        // 시간 초과 x
        // value 범위가 10만이라 sum 갱신을 위한 선형탐색도 많은 시간 필요
        sum += R - L + 1

//        // 시간 초과
//        for(i in 1..max_value) {
//            //print("${count[i]} ")
//            if(count[i] == 1) {
//                sum++
//            }
//        }
        //println()
    }

    sb.append(sum)
    println(sb)
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
