package boj.doing

import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

// 4 x 4 격자지만 넉넉하게 6 x 6 (앞 - 뒤 , 위 - 아래), 새번째 인자는 방향(현재 어느방향을 바라보고 있는지)
var map = Array(52) { IntArray(52) { -1 } }

var dist = Array(52) { IntArray(52) { -1 } }
var copyOfDist = Array(52) { IntArray(52) { -1 } }

var dx = intArrayOf(0, -1, 0, 1)
var dy = intArrayOf(-1, 0, 1, 0)

var L: Int = 0 // 세로, Length
var T: Int = 0 // 가로, Traverse

//이 문제는 처음에 다 넣어놓으면 안될거 같은데
var Q: Queue<Coord> = LinkedList()

data class Coord(var x: Int, var y: Int)

fun main() {
    solve()
}

fun solve() {
    input()
    pro()
}

fun input() {
    L = scan.nextInt()
    T = scan.nextInt()

    for (i in 1..L) {
        val temp = scan.nextLine()
        for (j in 1..T) {
            if (temp[j - 1] == 'W') {
                map[i][j] = 0
            } else {
                map[i][j] = 1
            }
        }
    }

//    for (i in 1..L) {
//        for (j in 1..T) {
//            print(map[i][j])
//        }
//        println()
//    }
}

fun pro() {
    //양 끝점 알아내기
    //모든 점에서 bfs 를 수행해보고 가장 큰 값이 나오면 그게 정답아님? 완탐

    //bfs 의 시작점을 지정해서 탐색 시작
    var maxDist = -1
    for (i in 1..L) {
        for (j in 1..T) {
            if (map[i][j] == 1) {
                bfs(i, j)

//                for (i in 1..L) {
//                    for (j in 1..T) {
//                        print("${dist[i][j]}   ")
//                    }
//                    println()
//                }

                //val maxLength = Math.max(maxLength, findMaxLength())
                maxDist = maxDist.coerceAtLeast(findMaxDist())
                initDist()
            }
        }
    }

    print(maxDist)
}

fun bfs(x: Int, y: Int) {
    Q.add(Coord(x, y))
    //방문 처리
    dist[x][y] = 0
    while (!Q.isEmpty()) {
        val temp = Q.poll()
        val cx = temp.x
        val cy = temp.y

        for (i in 0 until 4) { // 현재 방향으로부터 팔방탐색
            val nx = cx + dx[i]
            val ny = cy + dy[i]

            if (canGo(nx, ny)) {
                dist[nx][ny] = dist[cx][cy] + 1
                Q.add(Coord(nx, ny))
            }
        }
    }
}

fun findMaxDist(): Int {
    var maxDist = -1
    for (i in 1..L) {
        for (j in 1..T) {
            maxDist = maxDist.coerceAtLeast(dist[i][j])
        }
    }
    //println(maxDist)
    return maxDist
}

fun initDist() {
    for (i in 1..L) {
        for (j in 1..T) {
            dist[i][j] = copyOfDist[i][j]
        }
    }
}

/** 범위 밖을 벗어나는지 검사  */
fun inRange(nx: Int, ny: Int): Boolean {
    return nx >= 1 && ny >= 1 && nx <= L && ny <= T
}

fun canGo(nx: Int, ny: Int): Boolean {
    return inRange(nx, ny) && map[nx][ny] == 1 && dist[nx][ny] == -1
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
