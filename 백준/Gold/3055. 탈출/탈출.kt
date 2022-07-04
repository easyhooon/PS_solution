package doing

import java.io.*
import java.util.*

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var R: Int = 0
internal var C: Int = 0
internal var result: Int = 0

// 2차원 배열
lateinit var board: Array<String>
lateinit var blank: ArrayList<Pair<Int, Int>>
lateinit var water: ArrayList<Pair<Int, Int>>

lateinit var water_dist: Array<Array<Int>>
lateinit var hog_dist: Array<Array<Int>>

internal var dx = intArrayOf(1, 0, 0, -1)
internal var dy = intArrayOf(0, 1, -1, 0)

fun main() {
    solve()
}

fun solve() {
    input()

    // 동시에 채우려 하지말고 하나씩
    // 물이 칸마다 몇초 뒤에 차는지 먼저 계산
    water_bfs()
    hog_bfs()

//    repeat(R) { i ->
//        repeat(C) { j ->
//            print(water_dist[i][j])
//        }
//        println()
//    }
//    println()
//
//    repeat(R) { i ->
//        repeat(C) { j ->
//            print(hog_dist[i][j])
//        }
//        println()
//    }
//    println()

    if(result == -1) {
        sb.append("KAKTUS")
        println(sb.toString())
    }
    else {
        sb.append(result)
        println(sb.toString())
    }
}

fun input() {
    R = scan.nextInt()
    C = scan.nextInt()

    board = Array(R + 1) { "" }
    blank = ArrayList<Pair<Int, Int>>()
    water_dist = Array(R + 1) { Array(C + 1) { -1 } }
    hog_dist = Array(R + 1) { Array(C + 1) { -1 } }
    water = ArrayList<Pair<Int, Int>>()

    repeat(R) { i ->
        val list = scan.nextLine()
        board[i] = list
    }

    repeat(R) { i ->
        repeat(C) { j ->
            if (board[i][j] == '*') {
                water.add(Pair(i, j))
            }
        }
    }
}

fun isIn(x: Int, y: Int): Boolean {
    return 0 <= x && x < R && 0 <= y && y < C
}

fun water_bfs() {
    val q = LinkedList<Pair<Int, Int>>()

    // 시작점이 여러점이므로 한번에 que에 넣어주고 시작
    repeat(water.size) { i ->
        q.add(Pair(water[i].first, water[i].second))
        water_dist[water[i].first][water[i].second] = 0
    }

    while (!q.isEmpty()) {  // 더 확인할 점이 없다면 정지
        val coord = q.poll()
        val cx = coord.first
        val cy = coord.second

        repeat(4) { i ->
            val nx = cx + dx[i]
            val ny = cy + dy[i]

            // 굳이 바이러스를 직접 퍼뜨리지 않고(맵 배열 값 갱신) 방문했다라고 체크만 해주면 된다.
            if (isIn(nx, ny) && board[nx][ny] != 'D' && board[nx][ny] != 'X' && water_dist[nx][ny] == -1) {
                q.add(Pair(nx, ny))
                water_dist[nx][ny] = water_dist[cx][cy] + 1
            }
        }
    }
}

fun hog_bfs() {
    val q = LinkedList<Pair<Int, Int>>()
    repeat(R) { i ->
        repeat(C) { j ->
            if(board[i][j] == 'S') {
                q.add(Pair(i, j))
                hog_dist[i][j] = 0
            }
        }
    }

    while (!q.isEmpty()) {  // 더 확인할 점이 없다면 정지
        val coord = q.poll()
        val cx = coord.first
        val cy = coord.second

        repeat(4) { i ->
            val nx = cx + dx[i]
            val ny = cy + dy[i]

            // 굳이 바이러스를 직접 퍼뜨리지 않고(맵 배열 값 갱신) 방문했다라고 체크만 해주면 된다.
            if (isIn(nx, ny) && board[nx][ny] != '*' && board[nx][ny] != 'X' && compare(nx, ny, cx, cy) &&  hog_dist[nx][ny] == -1) {
                q.add(Pair(nx, ny))
                hog_dist[nx][ny] = hog_dist[cx][cy] + 1
            }
        }
    }

    repeat(R) { i ->
        repeat(C) { j ->
            if(board[i][j] == 'D') {
                result = hog_dist[i][j]
            }
        }
    }
}

fun compare(nx: Int, ny: Int, cx: Int, cy: Int): Boolean {
    if(board[nx][ny] != 'D') {
        //water_dist와 hog_dist 가 둘다 -1 일때 (water가 벽에 막혀서 도달하지 못한 '.' 에 대한 경우)
        if(board[nx][ny] == '.' && water_dist[nx][ny] == -1) {
            return true
        }
        return water_dist[nx][ny] > hog_dist[cx][cy] + 1
    }
    return true
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