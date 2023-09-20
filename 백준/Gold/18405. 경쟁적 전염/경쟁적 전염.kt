import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

var N = 0
var K = 0
var S = 0
var X = 0
var Y = 0

lateinit var board: Array<IntArray>
lateinit var check: Array<BooleanArray>

var q: Queue<Pair<Int,Int>> = LinkedList()
var temp_q: Queue<Pair<Int,Int>> = LinkedList()

val dx = intArrayOf(0, 1, 0, -1)
val dy = intArrayOf(-1, 0, 1, 0)

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    K = scan.nextInt()

    board = Array(N + 1) { IntArray(N + 1) }
    check = Array(N + 1) { BooleanArray(N + 1) }

    for (i in 1..N) {
        for (j in 1..N) {
            board[i][j] = scan.nextInt()
        }
    }

    //virus 넘버링
    for (v in 1..K) {
        for (i in 1..N) {
            for (j in 1..N) {
                if (board[i][j] == v)
                    q.offer(Pair(i, j))
            }
        }
    }

    S = scan.nextInt()
    X = scan.nextInt()
    Y = scan.nextInt()
}

fun pro() {
    bfs(0)
}

fun bfs(sec: Int) {
    var second = sec
    while (second != S) {
        while (!q.isEmpty()) {
            val top = q.poll()
            val cx = top.first
            val cy = top.second

            check[cx][cy] = true

            for (k in 0 until 4) {
                val nx = cx + dx[k]
                val ny = cy + dy[k]

                if (nx in 1..N && ny in 1..N) {
                    if (!check[nx][ny] && board[nx][ny] == 0) {
                        check[nx][ny] = true
                        board[nx][ny] = board[cx][cy]
                        //show()
                        temp_q.offer(Pair(nx, ny))
                    }
                }
            }
        }
        second += 1

        while (!temp_q.isEmpty()) {
            val element = temp_q.poll()
            q.offer(element)
        }
    }

    sb.append(board[X][Y]).append("\n")
    print(sb.toString())
}

fun show() {
    for(i in 1..N) {
        for (j in 1..N) {
            print("${board[i][j]} ")
        }
        println()
    }
    println()
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