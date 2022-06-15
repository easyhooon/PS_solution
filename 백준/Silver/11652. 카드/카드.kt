import java.io.*
import java.util.*
import kotlin.collections.HashMap

val scan = FastReader()
val sb = StringBuilder()


var N = 0
lateinit var arr: LongArray
//lateinit var cardMap: HashMap<Long, Int>

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    arr = LongArray(N)
    //cardMap = hashMapOf()
    for(i in 0 until N) {
        arr[i] = scan.nextLong()
        //cardMap[arr[i]] = 0
    }
}

fun pro() {
//    for(i in 0 until N) {
//        cardMap[arr[i]] = (cardMap[arr[i]] ?: 0) + 1
//    }
//    println(cardMap)
//    println(Collections.max(cardMap.values))
//    println(cardMap.filterValues { it == Collections.max(cardMap.values)}.keys.first().toLong())
    //정렬한번 해주고 선형탐색 -> 숫자가 변하기전까지 count
    Arrays.sort(arr)
//    println(arr.joinToString())
    var now = arr[0]
    var cnt = 0
    var max_index = 0L
    var max_cnt = 0
    for(i in 0 until N) {
        if (now == arr[i]) {
            cnt++
//            println("cnt: $cnt")
        }
        else {
            if(cnt > max_cnt) {
                max_cnt = cnt
                max_index= now
            }
            now = arr[i]
            cnt = 1
        }
        if(i == N-1) {
            if(cnt > max_cnt) {
                max_cnt = cnt
                max_index= now
            }
        }
    }

    println(max_index)
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
