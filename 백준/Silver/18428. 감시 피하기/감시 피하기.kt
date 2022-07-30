import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

var N = 0
var wall_cnt = 0
var student_cnt = 0
lateinit var board: Array<CharArray>
lateinit var base_board: Array<CharArray>
lateinit var blank: Array<IntArray>
lateinit var check: Array<BooleanArray>
lateinit var base_check: Array<BooleanArray>
var teacher = ArrayList<Pair<Int, Int>>()
var flag = false

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()

    board = Array(N + 1) { CharArray(N + 1) }
    base_board = Array(N + 1) {CharArray(N+1) }
    check = Array(N+1) { BooleanArray(N+1) }
    base_check = Array(N+1) { BooleanArray(N+1)}
    blank = Array(N * N + 1) { IntArray(2) }
    for (i in 1..N) {
        for (j in 1..N) {
            board[i][j] = scan.next().first()
            if (board[i][j] == 'T') {
                teacher.add(Pair(i, j))
            }
            if (board[i][j] == 'S') {
                student_cnt += 1
            }
        }
    }

    for (i in 1..N) {
        for (j in 1..N) {
            if (board[i][j] == 'X') {
                // 장애물이 들어갈 수 있는 자리
                wall_cnt += 1
                blank[wall_cnt][0] = i
                blank[wall_cnt][1] = j
            }
        }
    }
}

fun pro() {
    dfs(1, 0)

    if(flag) {
        println("YES")
    } else {
        println("NO")
    }
}

//selected_cnt 가 3 미만인채로 idx만 계속 오르는 경우 존재
fun dfs(idx: Int, selected_cnt: Int) {
    if (selected_cnt == 3) {
        //show(board)

        copy()

        val find_cnt = search()

        //show(board)

        if (find_cnt == 0) {
            flag = true
        }

        clear()

        return
    }

    if(idx > wall_cnt) return

    //idx 칸에 벽을 세움
    board[blank[idx][0]][blank[idx][1]] = 'O'
    dfs(idx + 1, selected_cnt + 1)
    //idx 칸에 벽을 세운거 해제
    board[blank[idx][0]][blank[idx][1]] = 'X'

    //idx 칸에 벽을 안세움
    dfs(idx + 1, selected_cnt)
}

fun show(graph: Array<CharArray>) {
    for(i in 1..N) {
        for(j in 1..N) {
            print("${graph[i][j]} ")
        }
        println()
    }
    println()
}

fun search(): Int {
    var find_cnt = 0

    for (coord in teacher) {
        val cx = coord.first
        val cy = coord.second

        //사이에 벽이 있으면 학생을 찾지 못함
        for (i in cx+1..N) {
            if(board[i][cy] == 'O') {
                //println("$i $cy")
                break
            }
            if (board[i][cy] == 'S') {
                //println("$i $cy")
                if(!check[i][cy]) {
                    check[i][cy] = true
                    find_cnt+=1
                }

            }
            board[i][cy] = 'C'
        }

        for (i in cx-1 downTo 1) {
            if(board[i][cy] == 'O') {
                //println("$i $cy")
                break
            }

            if (board[i][cy] == 'S') {
                //println("$i $cy")
                if(!check[i][cy]) {
                    check[i][cy] = true
                    find_cnt+=1
                }
            }
            board[i][cy] = 'C'

        }

        for (j in cy+1..N) {
            if(board[cx][j] == 'O') {
                //println("$i $cy")
                break
            }

            if (board[cx][j] == 'S') {
                //println("$cx $j")
                if(!check[cx][j]) {
                    check[cx][j] = true
                    find_cnt+=1
                }
            }
            board[cx][j] = 'C'
        }
        for (j in cy-1 downTo 1) {
            if(board[cx][j] == 'O') {
                //println("$cx $i")
                break
            }

            if (board[cx][j] == 'S') {
                //println("$cx $j")
                if(!check[cx][j]) {
                    check[cx][j] = true
                    find_cnt+=1
                }
            }
            board[cx][j] = 'C'
        }
    }

    return find_cnt
}


fun copy() {
    for(i in 1..N) {
        for(j in 1..N) {
            base_board[i][j] = board[i][j]
        }
    }
}

fun clear() {
    //이런식으로 클리어작업을 하면 적용안된다
    //board = base_board
    //check = base_check

    for(i in 1..N) {
        for(j in 1..N) {
            board[i][j] = base_board[i][j]
        }
    }
    for(i in 1..N) {
        for(j in 1..N) {
            check[i][j] = base_check[i][j]
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