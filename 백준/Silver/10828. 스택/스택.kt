package boj.doing

// lower_bound 와 upper_bound 의 정의.. 도움
// ans 초기값, ans 갱신 위치
import java.io.*
import java.util.*

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0
lateinit var stack: MutableList<Int>

fun main() {
    solve()
}

fun solve() {
    input()
    solution()
}

fun input() {
    N = scan.nextInt()
}

// mutablelist. add 를 통해 원소가 들어가는(채워지는) 순서 확인
fun solution() {
    stack = mutableListOf()

    for(i in 1..N) {
        val command = scan.next()

        if(command == "top") {
            if(stack.isNotEmpty()) {
                println(stack[stack.size-1])
            }
            else {
                println("-1")
            }
        }
        else if(command == "size") {
            println(stack.size)
        }

        else if(command == "pop") {
            if(stack.isNotEmpty()) {
                println(stack[stack.size-1])
                stack.removeLast()
            }
            else {
                println(-1)
            }
        }

        else if(command == "empty") {
            if(stack.isNotEmpty()){
                println(0)
            }
            else {
                println(1)
            }
        }
        else {
            //push
            val number = scan.nextInt()
            stack.add(number)
        }
        //println("[${stack.joinToString()}]")
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