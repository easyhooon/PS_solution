import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

val br = BufferedReader(InputStreamReader(System.`in`))
val st = StringTokenizer(br.readLine())

var dx = intArrayOf(-1, 1, 0, 0)
var dy = intArrayOf(0, 0, -1, 1)

var n = 0
var m = 0
var cnt = 0

var q: Queue<IntArray> = LinkedList()
var fire: Queue<IntArray> = LinkedList()
lateinit var map: Array<CharArray>

fun main() {
    input()
    pro()
}

fun input() {
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()

    map = Array(n) { CharArray(m) }

    for (i in 0 until n) {
        val s = br.readLine()
        for (j in 0 until m) {
            map[i][j] = s[j]
            if (map[i][j] == 'J') {
                q.add(intArrayOf(i, j, 0))
            }
            if (map[i][j] == 'F') {
                fire.add(intArrayOf(i, j, 0))
            }
        }
    }
}

fun pro() {
    if (bfs()) {
        println(cnt)
    } else {
        println("IMPOSSIBLE")
    }
}

fun bfs(): Boolean {
    while (!q.isEmpty()) {
        var size = fire.size
        //불 먼저 이동
        for (i in 0 until size) {
            val t = fire.poll()
            val fireX = t[0]
            val fireY = t[1]
            val fireTime = t[2]

            for (d in 0..3) {
                val nx = fireX + dx[d]
                val ny = fireY + dy[d]

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue
                }

                if (map[nx][ny] == '.' || map[nx][ny] == 'J') {
                    map[nx][ny] = 'F'
                    fire.add(intArrayOf(nx, ny, fireTime + 1))
                }
            }
        }

//        for (i in 0 until n) {
//            for (j in 0 until m) {
//                print(map[i][j].toString() + " ")
//            }
//            println()
//        }

        size = q.size
        for (i in 0 until size) {
            val t = q.poll()
            val x = t[0]
            val y = t[1]
            val time = t[2]

            for (d in 0..3) {
                val nx = x + dx[d]
                val ny = y + dy[d]

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    cnt = time + 1
                    return true
                }

                if (map[nx][ny] == '.') {
                    map[nx][ny] = 'J'
                    q.add(intArrayOf(nx, ny, time + 1))
                }
            }
        }
    }

    return false
}
