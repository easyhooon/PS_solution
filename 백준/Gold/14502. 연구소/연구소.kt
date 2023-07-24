// 도움 받음
import java.io.*
import java.lang.Integer.max
import java.util.*

internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0
internal var M: Int = 0
internal var cnt: Int = 0
internal var result: Int = 0
internal var wall_cnt: Int = 0

// 2차원 배열
lateinit var board: Array<Array<Int>>
lateinit var base: Array<Array<Int>>
//lateinit var selected: Array<Array<Int>>
lateinit var virus: ArrayList<Pair<Int,Int>>
lateinit var blank:Array<Array<Int>>

lateinit var visit: Array<Array<Boolean>>

internal var dx = intArrayOf(1, 0, 0, -1)
internal var dy = intArrayOf(0, 1, -1, 0)

fun main() {
    solve()
}

fun solve() {
    input()

    //벽을 심고
    dfs(1, 0)

    //다 퍼뜨렸으면 안전 지대 count

    //안전 지대 값 갱신하고 초기화 후 반복

    sb.append(result)
    println(sb.toString())
}

fun input() {
    N = scan.nextInt()
    M = scan.nextInt()

    board = Array(N + 1) { Array(M + 1) { 0 } }
//    selected = Array(N + 1) { Array(N + 1) { 0 } }
    visit = Array(N + 1) { Array(M + 1) { false } }
    virus = ArrayList<Pair<Int,Int>>()
    blank = Array(N * M + 1){ Array(2) { 0 } }

    for (i in 1..N) {
        for (j in 1..M) {
            board[i][j] = scan.nextInt()
            if(board[i][j] == 2){
                virus.add(Pair(i,j))
            }
        }
    }
    base = board

    for(i in 1..N) {
        for(j in 1.. M) {
            if (board[i][j] == 0) {
                //벽이 들어갈 수 있는 자리
                wall_cnt++;
                blank[wall_cnt][0] = i;
                blank[wall_cnt][1] = j;
            }
        }
    }
}

//벽 심기
//fun dfs(k: Int) {
//    if (k == 3) {
//        //다 골랐다
//        //탐색 시작
//        bfs()
//        return // <- 중요
//    } else {
//        for (i in 1..N) {
//            for (j in 1..M) {
//                //빈 칸이고 벽이 아닌칸 만
//                // 중복 없이 세 점을 골르는 경우의 수
//                if (board[i][j] == 0 && selected[i][j] == 0) {
//                    selected[i][j] = 1
//                    dfs(k + 1)
//                    selected[i][j] = 0
//                }
//            }
//        }
//    }
//}

// idx 번째 빈 칸에 벽을 세울 지 말 지 결정해야 하고, 이 전까지 selected_cnt 개의 벽을 세웠다.
fun dfs(idx: Int, selected_cnt: Int) {
    if (selected_cnt == 3) {  // 3 개의 벽을 모두 세운 상태
        //3개 다 심었으면 바이러스 퍼뜨리기 시작
        bfs()
        return
    }
    if (idx > wall_cnt) return  // 더 이상 세울 수 있는 벽이 없는 상태

    //완전 탐색 중복없이 선택 플로우

    //idx 칸에 벽을 세움, 채우고 재귀호출
    board[blank[idx][0]][blank[idx][1]] = 1
    dfs(idx + 1, selected_cnt + 1)

    //idx 칸에 벽을 안세움, 안채우고 재귀호출
    board[blank[idx][0]][blank[idx][1]] = 0
    dfs(idx + 1, selected_cnt)
}

fun isIn(x: Int, y: Int): Boolean {
    return 0 < x && x < N + 1 && 0 < y && y < M + 1
}

fun clear() {
    visit = Array(N + 1) { Array(M + 1) { false } }
}

fun bfs() {
    val q = LinkedList<Pair<Int, Int>>()
    // start 는 방문 가능한 점이므로 que 에 넣어준다.

    clear()

    repeat(virus.size) { i ->
        q.add(Pair(virus[i].first, virus[i].second))
        visit[virus[i].first][virus[i].second] = true
    }

    while (!q.isEmpty()) {  // 더 확인할 점이 없다면 정지
        val coord = q.poll()
        val cx = coord.first
        val cy = coord.second

        repeat(4) { i ->
            val nx = cx + dx[i]
            val ny = cy + dy[i]

            if (isIn(nx, ny) && board[nx][ny] == 0 && !visit[nx][ny]) {
                q.add(Pair(nx, ny))
                visit[nx][ny] = true
            }
        }
    }

    cnt = 0

    for (i in 1..N) {
        for (j in 1..M) {
            if (board[i][j] == 0 && !visit[i][j]) {
                cnt++
            }
        }
    }

    result = max(result, cnt)
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