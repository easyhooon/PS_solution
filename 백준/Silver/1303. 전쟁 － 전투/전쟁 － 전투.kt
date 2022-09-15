import java.util.*

lateinit var arr: Array<CharArray>
lateinit var visited: Array<BooleanArray>

var dx = intArrayOf(1, 0, -1, 0)
var dy = intArrayOf(0, 1, 0, -1)
var N = 0
var M = 0

fun main() {
    val (m, n) = readln().split(" ").map { it.toInt() }
    N = n
    M = m

    arr = Array(N) { CharArray(M) }
    visited = Array(N) { BooleanArray(M) }

    for (i in 0 until N) {
        val tmp = readln()
        for (j in 0 until M) {
            arr[i][j] = tmp[j]
        }
    }

    solution(N, M)
}

fun solution(N: Int, M: Int) {
    var wTotalCount = 0
    var bTotalCount = 0

    for (i in 0 until N) {
        for (j in 0 until M) {
            if (!visited[i][j]) {
                if (arr[i][j] == 'W') {
                    wTotalCount += bfs(i, j, 'W')
                } else if (arr[i][j] == 'B') {
                    bTotalCount += bfs(i, j, 'B')
                }
            }
        }
    }
    println("$wTotalCount $bTotalCount")
}

fun isIn(x: Int, y: Int): Boolean {
    return x in 0 until N && y in 0 until M
}

fun bfs(x: Int, y: Int, color: Char): Int {
    var cnt = 1
    
    val q = LinkedList<Pair<Int, Int>>()
    q.offer(Pair(x, y))
    visited[x][y] = true

    while(q.isNotEmpty()) {
        val (cx, cy) = q.poll()
        for (k in 0..3) {
            val nx = cx + dx[k]
            val ny = cy + dy[k]
            if (isIn(nx, ny)) {
                if (arr[nx][ny] == color && !visited[nx][ny]) {
                    cnt++
                    visited[nx][ny] = true
                    q.add(Pair(nx, ny))
                }
            }
        }
    }

    return cnt * cnt
}