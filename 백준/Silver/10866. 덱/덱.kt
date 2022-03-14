package boj.doing

// lower_bound 와 upper_bound 의 정의.. 도움
// ans 초기값, ans 갱신 위치
import java.io.*
import java.util.*
import kotlin.collections.ArrayDeque

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0
lateinit var deque: ArrayDeque<Int>

fun main() {
    solve()
}

fun solve() {
    input()
    solution()
}

fun input() {
    N = scan.nextInt()

//    val deque = ArrayDeque<Int>()
//
//    // 아래 함수들은 모두 first last 모두 가지고 있지만 생략
//    deque.addLast(1) // exception 반환
//    deque.offerFirst(2) // exception 반환 X
//
//    deque.removeFirst() // exception 반환
//    deque.pollLast() // exception 반환 X
//
//    deque.peekFirst()
//    deque.peekLast()
}

fun solution() {
    deque = ArrayDeque()

    for(i in 1..N) {
        val command = scan.next()

        if(command == "front") {
            if(deque.isNotEmpty()) {
                println(deque[deque.size-1])
                //println(deque[0])
            }
            else {
                println("-1")
            }
        }
        else if(command == "back") {
            if(deque.isNotEmpty()) {
                println(deque[0])
                //println(deque[deque.size-1])
            }
            else {
                println("-1")
            }
        }

        else if(command == "size") {
            println(deque.size)
        }

        else if(command == "pop_front") {
            if(deque.isNotEmpty()) {
                println(deque[deque.size-1])
                deque.removeLast()
            }
            else {
                println(-1)
            }
        }

        else if(command == "pop_back") {
            if(deque.isNotEmpty()) {
                println(deque[0])
                deque.removeFirst()
            }
            else {
                println(-1)
            }
        }

        else if(command == "empty") {
            if(deque.isNotEmpty()){
                println(0)
            }
            else {
                println(1)
            }
        }
        else if (command == "push_front") {
            //push
            val number = scan.nextInt()
            deque.addLast(number)
        }

        else {
            //push_back
            val number = scan.nextInt()
            deque.addFirst(number)
        }
        //println("[${deque.joinToString()}]")
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

