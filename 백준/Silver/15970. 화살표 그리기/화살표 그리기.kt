import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()
var N = 0

var sum = 0

//점 개수 10^5 n^2 으로 못품
//탐색의 방향이 정방향과 역방향이 존재한다.
//정답은 근처에 점들이다. 하지만 가장 근처의 점 들 앞뒤로+-1 점들의 색깔이 다를수가 있는데?
//점의 색깔은 점의 수 만큼 있을 수 있다.
//ㄴ색깔이 다른 점들을 따로 관리를 해야할 것 같은데.
//ㅇㅇ 색깔마다 arrayList 를 만들어줘야한다.
//그럼 동적으로 arrayList 를 만들어야하는거아님? 어떻게? 이거를 못함... 윽 코틀린의 단점 ?? ㄴㄴ 자바랑 다른게 없어!!!
//-> 색깔이 n개 이하이니까 n+1개 배열을 만들면되잖아 ㅇㅇ;
//data class Point(val location: Int, val color: Int)

lateinit var points: Array<ArrayList<Int>>

//모든 점에 대해서 같은 색깔을 가진 다른 점이 항상 존재한다. 따라서 각 점 p에서 시작하여 위 조건을 만족하는 q로 가는 하나의 화살표를 항상 그릴 수 있다.
//거리는 0 부터 색깔이 '1부터 N까지'
//따라서 index n번째를 신경 써줘야
fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
//    points = Array<ArrayList<Int>>(N + 1) { ArrayList() }
    points = Array(N + 1) { ArrayList<Int>() }
    for (i in 0 until N) {
        val loc = scan.nextInt()
        val col = scan.nextInt()
        points[col].add(loc)
    }
}

fun pro() {

    for (i in 1..N) {
        points[i].sort()
        //println(points[i])
    }

    for (color in 1..N) {
        sum += getSum(points[color])
    }

    sb.append(sum)
    print(sb)
}

fun getSum(points: ArrayList<Int>): Int {
    var sum = 0
    for (i in 0 until points.size) {

        if (i == 0) {
            //println("${points[i + 1] - points[i]}")
            //개수가 1개일때 예외처리 해주어야함...ㄴㄴ
            sum += points[i + 1] - points[i]
        } else if (i == points.size - 1) {
            //println("${points[points.size - 1] - points[points.size - 2]}")
            sum += points[points.size - 1] - points[points.size - 2]
        } else {
//            println(
//                "${
//                    Math.min(
//                        points[i] - points[i - 1],
//                        points[i + 1] - points[i]
//                    )
//                }"
//            )
            sum += Math.min(
                points[i] - points[i - 1],
                points[i + 1] - points[i]
            )
        }
    }

    return sum
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
