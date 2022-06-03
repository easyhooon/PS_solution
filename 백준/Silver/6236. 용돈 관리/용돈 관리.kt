import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

var N = 0
var M = 0
var ans = 0

lateinit var price: IntArray

//이건 순서가 좀 중요한데..? 이분탐색으로 풀려면 정렬을 시켜야잖아 정렬해놓으면 같은 배열이여도 m이 달라지는데
//정렬을 하지 않는 ㄴㄴ 배열을 정렬해놓고 사용하는게 아닌 1~최댓값 사이의 전체 정수를 배열로 두고 푸는 이분탐색 문제 ㅇㅇ
fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    M = scan.nextInt()
    price = IntArray(N)
    for(i in 0 until N) {
        price[i] = scan.nextInt()
    }
}

fun pro() {
    //Arrays.sort(price, 0, N)
    //println(price.contentToString())

    val L = 1
    //val R = 1000000
    val R = price.sum() // 최대값 산정 m이 1일때 -> 사용할 금액의 총합 

    ans = binary_search(L, R)
    sb.append(ans)
    print(sb)
}

fun binary_search(L: Int, R: Int): Int {
    var L = L
    var R = R

    ans = Int.MAX_VALUE

    while (L <= R) {
        val mid = ((L + R) / 2)
        if (determination(mid)) {
            R = mid - 1
            ans = mid
        } else {
            L = mid + 1
        }
    }
    return ans
}

fun determination(mid: Int): Boolean {
    var cnt = 1
    var sum = mid

    for (i in 0 until N) {
        // 돈을 인출해도 그날 사용해야할 금액보다 적은 경우 false
        if (mid < price[i])
            return false

        // 남은 돈이 그날 사용할 금액보다 적은 경우
        if(sum - price[i] < 0) {
            //집어넣고 다시  mid 만큼 인출
            sum = mid
            cnt++
        }
        sum -= price[i]
    }
    return cnt <= M

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
