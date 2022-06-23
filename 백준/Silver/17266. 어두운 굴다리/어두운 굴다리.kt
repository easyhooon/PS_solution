import java.io.*
import java.util.*

//주석 -> TLE 풀이
val scan = FastReader()
val sb = StringBuilder()

var N = 0
var M = 0
var ans: Int = 0

lateinit var location: IntArray
//lateinit var check: BooleanArray
//lateinit var copyOfCheck: BooleanArray

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    M = scan.nextInt()
    location = IntArray(M)
//    check = BooleanArray(N)
//    copyOfCheck = BooleanArray(N)

    for(i in 0 until M) {
        location[i] = scan.nextInt()
    }
}

fun pro() {
//    var max_distance = -1
//    // 괜히 최소거리 잡는게 까다롭네 그냥 1부터하는게 나을듯...
//    for (i in 0 until M-1) {
//        for (j in 1 until M) {
//            val distance = location[j] - location[i]
//            max_distance = Math.max(max_distance, distance)
//        }
//    }

//    val L: Int = max_distance
    val L: Int = 1
    val R: Int = N

//    determination test
//    val result = determination(1)

    ans = binarySearch(L, R)
    sb.append(ans)
    print(sb)
}

fun binarySearch(L: Int, R: Int): Int {
    var L = L
    var R = R

    while (L <= R) {
        val mid = ((L + R) / 2)
        if (determination(mid)) {
            ans = mid
            R = mid - 1
        } else {
            L = mid + 1
        }
    }
    return ans

    // 1 + 5 = 6 -> mid = 3 -> true -> R = 2
    // 1 + 2 = 3 -> mid = 1 -> false -> L = 2
    // 2 + 2 = 4 -> mid = 2 -> true -> R = 1 -> return 2
}

//가로등이 모든 거리를 비출 수 있는지
//조건을 수식으로 표현할 수 있는게 이분탐색의 핵심
fun determination(mid: Int): Boolean {
//    println("mid: $mid")
//    //통배열 복사가 안먹는건가 - 그런듯
//    check = copyOfCheck
//    for (i in 0 until N) {
//        check[i] = copyOfCheck[i]
//        print("${check[i]} ")
//    }
//    println()

//    var result = true
//    for(i in location) {
//        //불빛의 전체 거리를 구할까? 그것과 굴다리 길이를 비교
//        //겹치는거 고려하는게 너무 빡셀거같은데
//        //굳이 정확하게 구해야하나?
//        //각 지점에 대한 커버거리의 합이 굴다리의 길이보다만 크면되지 않을까 ㄴㄴ 양 끝점에 있는 경우 같은거 해당안됨
//        //어떻게 해야 판단을 할 수 있을까
//        //전부 채우기 위한 최소거리는 일단 각 지점 사이 거리보단 커야한다.
//        //전봇대간의 최대 사이거리 == L 로 잡으면 좋을 것 같다.
//        //check 배열 쓰는게 확실할거같은데 일단 무식하게 풀어서 답 맞아보고 해설보자
//        var left = i - mid
//        //if mid == 1
//        //2 - 1 == 1
//        //4 - 1 == 3
//        if(left < 0) {
//            //왼쪽 길이가 시작점보다 왼쪽으로 가버린 경우
//            left = 0
//        }
//
//        var right = i + mid
//        //if mid == 1
//        //2 + 1 = 3
//        //4 + 1 == 5
//        if(right > N) {
//            //오른쪽 길이가 끝점보다 오른족으로 가버린 경우
//            right = N
//        }
//
//        // 1 until 3
//        // false true true false false
//        // 3 until 5
//        // false false false true true
//        // false true true true true
//        for(j in left until right) {
//            if(check[j]) continue
//            else check[j] = true
//        }
//    }
//
//    for (i in 0 until N) {
////        print("${check[i]} ")
//        if (!check[i]) {
//            result = false
//        }
//    }
////    println()
////    println("result: $result")
//
//    return result

    var endPoint = 0

    if (location.size == 1) {
        return location[0] - mid <= 0 && mid + location[0] >= N
    }

    for( i in location.indices) {
        if (location[i] - mid <= endPoint) {
            endPoint = location[i] + mid
        }
        else {
            return false
        }
    }

    // distance 를 모두 덮는 경우
    return endPoint >= N
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
