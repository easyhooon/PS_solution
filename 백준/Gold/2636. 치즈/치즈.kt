import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

var N: Int = 0
var M: Int = 0
var backupCount = 0

// 2차원 배열
// 한시간 뒤에 녹을 치즈 판단
// 닫힌 구멍에 접하고 있는 치즈를 이번에 녹지않는 치즈라고 판단할 수 있는 방법
// 공기를 기준으로
// visit 만으로 판단 불가
lateinit var board: Array<Array<Int>>
//백업용
lateinit var backup: Array<Array<Int>>

lateinit var visit: Array<Array<Boolean>>
lateinit var dist: Array<Array<Int>>

internal var dx = intArrayOf(1, 0, 0, -1)
internal var dy = intArrayOf(0, 1, -1, 0)

fun main() {
    solve()
}

fun solve() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    M = scan.nextInt()

    board = Array(N + 1) { Array(M + 1) { 0 } }
    backup = Array(N + 1) { Array(M + 1) { 0 } }
    visit = Array(N + 1) { Array(M + 1) { false } }
    dist = Array(N + 1) { Array(M + 1) { -1 } }

    for (i in 1..N) {
        for (j in 1..M) {
            board[i][j] = scan.nextInt()
        }
    }

    for (i in 1..N) {
        for (j in 1..M) {
            backup[i][j] = board[i][j]
        }
    }
}

fun pro() {
    var time = 0
    while(remainCheeseCount() != 0) {
        clear()

        bfs()

        time++

        melting()
    }
    countBackup()

    sb.append(time).append('\n').append(backupCount)
    println(sb)
}

fun countBackup() {
    for(i in 1..N) {
        for(j in 1..M) {
            if(backup[i][j] == 1) {
                backupCount++
            }
        }
    }
}

fun clear() {
    for(i in 1..N) {
        for(j in 1..M) {
            visit[i][j] = false
        }
    }
}

fun remainCheeseCount(): Int {
    var cnt = 0
    for (i in 0 until N) {
        for (j in 0 until M) {
            if (board[i][j] == 1) cnt++
        }
    }
    return cnt
}

fun melting() {
    //백업
    for (i in 1..N) {
        for (j in 1..M) {
            backup[i][j] = board[i][j]
        }
    }
//    for(i in backup.indices) {
//        println(backup[i].contentToString())
//    }
//    println()

    for (i in 1..N) {
        for (j in 1..M) {
            if (board[i][j] == 1 && visit[i][j]) {
                board[i][j] = 0
            }
        }
    }
}

fun isIn(x: Int, y: Int): Boolean {
    return 0 < x && x < N + 1 && 0 < y && y < M + 1
}

fun bfs() {
    val q = LinkedList<Pair<Int, Int>>()

    q.add(Pair(1, 1))

    while (!q.isEmpty()) {
        val coord = q.poll()
        val cx = coord.first
        val cy = coord.second

        repeat(4) { i ->
            val nx = cx + dx[i]
            val ny = cy + dy[i]

            if (isIn(nx, ny)) {
                if (board[nx][ny] == 0 && !visit[nx][ny]) {
                    q.add(Pair(nx, ny))
                    visit[nx][ny] = true
                } else if (board[nx][ny] == 1) {
                    visit[nx][ny] = true
                }
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