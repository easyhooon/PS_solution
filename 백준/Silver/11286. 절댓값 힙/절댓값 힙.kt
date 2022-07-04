import java.io.*

import java.util.*

val scan = FastReader()
val sb = StringBuilder()

val q = PriorityQueue<Pair<Int, Int>>(kotlin.Comparator { a,b ->
    return@Comparator if(a.first == b.first) {
        a.second - b.second
    } else {
        a.first - b.first
    }
})

var N = 0

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
}

fun pro() {
    for (i in 0 until N) {
        //절대값으로 감싸서 q 에 넣어주면 어떻게 원래 값의 부호를 알 수 있지
        //data class 로 원래 부호까지 담아서 q에 넣어서 comparator 같은 걸로 정렬 가능할까
        val input = scan.nextInt()

        if (input == 0) {
            if (!q.isEmpty()) {
                val output = q.poll()
                if (output.second == -1) {
                    println(-output.first)
                } else {
                    println(output.first)
                }
            } else {
                println(0)
            }
        } else {
            if (input >= 0) {
                q.add(Pair(input, 1))
            } else {
                q.add(Pair(-input, -1))
            }
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
