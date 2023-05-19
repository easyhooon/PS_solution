import java.io.BufferedReader
import java.io.InputStreamReader

val dx = intArrayOf(0, 0, -1, 1)
val dy = intArrayOf(-1, 1, 0, 0)

fun dfs(dp: Array<IntArray>, graph: Array<IntArray>, n: Int, m: Int, x: Int, y: Int) {
    if (x == n - 1 && y == m - 1) {
        dp[x][y] = 1
        return
    }

    if (dp[x][y] != -1) {
        return
    }

    // 방문 처리
    dp[x][y] = 0

    for (i in 0 until 4) {
        val nx = x + dx[i]
        val ny = y + dy[i]

        if (nx in 0 until n && ny in 0 until m) {
            if (graph[x][y] > graph[nx][ny]) {
                dfs(dp, graph, n, m, nx, ny)
                dp[x][y] += dp[nx][ny]
            }
        }
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val graph = Array(n) { IntArray(m) }
    for (i in 0 until n) {
        graph[i] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val dp = Array(n) { IntArray(m) { -1 } }
    dfs(dp, graph, n, m, 0, 0)

//    for (i in 0 until n) {
//        for (j in 0 until m) {
//            print("${dp[i][j]} ")
//        }
//        println()
//    }
    println(dp[0][0])
}
