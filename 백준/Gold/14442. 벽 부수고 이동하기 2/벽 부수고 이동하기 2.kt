import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

var map = Array(1011) { IntArray(1011) }
var dist = Array(1011) { Array(1011) { IntArray(11) { -1 } } }

var dx = intArrayOf(0, -1, 0, 1)
var dy = intArrayOf(-1, 0, 1, 0)

var N: Int = 0
var M: Int = 0
var K: Int = 0

var Q: Queue<Node> = LinkedList()

data class Node(var x: Int, var y: Int, var cnt: Int)

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
    K = scan.nextInt()

    for (i in 1..N) {
        val temp = scan.nextLine()
        for (j in 1..M) {
            if (temp[j - 1] == '0') {
                map[i][j] = 0
            } else {
                map[i][j] = 1
            }
        }
    }
}

fun getConnection(cx: Int, cy: Int, cnt: Int): Vector<Node> {
    val con = Vector<Node>()
    for (i in 0 until 4) {
        val nx = cx + dx[i]
        val ny = cy + dy[i]

        if (inRange(nx, ny)) {
            if (map[nx][ny] == 0) {
                con.add(Node(nx, ny, cnt))
            } else {
                if (cnt + 1 <= K) {
                    con.add(Node(nx, ny, cnt + 1))
                }
            }
        }
    }
    return con
}

fun pro() {
    bfs()

    var minLength = Int.MAX_VALUE
    for (k in 0..K) {
        if(dist[N][M][k] != -1) {
            minLength = minLength.coerceAtMost(dist[N][M][k])
        }
    }

    if(minLength == Int.MAX_VALUE) {
        print(-1)
        return
    }

    print(minLength)
}


fun bfs() {
    Q.add(Node(1, 1, 0))
    dist[1][1][0] = 1

    while (!Q.isEmpty()) {
        val cur = Q.poll()
        val cx = cur.x
        val cy = cur.y
        val cnt = cur.cnt

        for (nxt in getConnection(cx, cy, cnt)) {
            if (dist[nxt.x][nxt.y][nxt.cnt] == -1) {
                dist[nxt.x][nxt.y][nxt.cnt] = dist[cx][cy][cnt] + 1
                Q.add(nxt)
            }
        }
    }
}

/** 범위 밖을 벗어나는지 검사  */
fun inRange(nx: Int, ny: Int): Boolean {
    return nx >= 1 && ny >= 1 && nx <= N && ny <= M
}

//fun canGo(nx: Int, ny: Int, cnt:Int): Boolean {
//    return inRange(nx, ny) && dist[nx][ny][cnt] == -1
//}

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
