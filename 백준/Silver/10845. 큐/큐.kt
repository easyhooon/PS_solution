import java.io.*
import java.util.*

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0
lateinit var queue: LinkedList<Int>

//val que = LinkedList<Int>()
//que.offer(3)
//que.poll()
//que.peek()

fun main() {
    solve()
}

fun solve() {
    input()
    solution()
}

fun input() {
    N = scan.nextInt()

//    var temp = LinkedList<Int>()
//    temp.offer(1)
//    temp.offer(2)
//    temp.offer(3)
}

fun solution() {
    queue = LinkedList()

    for(i in 1..N) {
        val command = scan.next()

        if(command == "front") {
            if(queue.isNotEmpty()) {
                //println(queue[queue.size-1])
                println(queue.peek())
            }
            else {
                println("-1")
            }
        }
        else if(command == "back") {
            if(queue.isNotEmpty()) {
                println(queue[queue.size-1])
            }
            else {
                println("-1")
            }
        }

        else if(command == "size") {
            println(queue.size)
        }

        else if(command == "pop") {
            if(queue.isNotEmpty()) {
                println(queue[0])
                queue.poll()
            }
            else {
                println(-1)
            }
        }

        else if(command == "empty") {
            if(queue.isNotEmpty()){
                println(0)
            }
            else {
                println(1)
            }
        }
        else {
            //push
            val number = scan.nextInt()
            queue.offer(number)
        }
//        println("[${queue.joinToString()}]")
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

