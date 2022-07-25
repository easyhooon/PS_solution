import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Math.max
import java.util.*

var n = 0
var m = 0

lateinit var board: Array<IntArray>
lateinit var dyA: Array<IntArray>
lateinit var dyB: Array<IntArray>

val br = BufferedReader(InputStreamReader(System.`in`))

var dirAx = intArrayOf(-1, 0)
var dirAy = intArrayOf(0, 1)
var dirBx = intArrayOf(-1, 0)
var dirBy = intArrayOf(0, -1)


fun main() {
    input()
    pro()
}

fun input() {
    var st = StringTokenizer(br.readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()

    //python 처럼 입력받기
    //val (n, m) = br.readLine().split(' ').map { it.toInt() }

    //println("$n $m")

    board = Array(n + 1) { IntArray(m + 1) }
    dyA = Array(n + 1) { IntArray(m + 1) }
    dyB = Array(n + 1) { IntArray(m + 1) }


    for (i in 1..n) {
        st = StringTokenizer(br.readLine())
        for (j in 1..m) {
            board[i][j] = st.nextToken().toInt()
        }
    }
//    for (i in 1..n) {
//        val list = br.readLine().split(' ')
//        for (j in 1..m) {
//            board[i][j] = list[j-1].toInt()
//        }
//    }

//    for (i in 1..n) {
//        for (j in 1..m) {
//            print("${board[i][j]} ")
//        }
//        println()
//    }
}

fun pro() {
    for (i in 1..n) {
        for (j in 1..m) {
            dyA[i][j] = -987654321
            dyB[i][j] = -987654321
        }
    }

    dyA[n][1] = board[n][1]
    for (j in 1..m) {
        for (i in n downTo 1) {
            for (k in 0 until 2) {
                val ni = i + dirAx[k]
                val nj = j + dirAy[k]
                if (ni in 1..n && nj in 1..m) {
                    dyA[ni][nj] = max(dyA[ni][nj], dyA[i][j] + board[ni][nj])
                }
            }
        }
    }

//    for (i in 1..n) {
//        for (j in 1..m) {
//            print("${dyA[i][j]} ")
//        }
//        println()
//    }


    dyB[n][m] = board[n][m]
    for (j in m downTo 1) {
        for (i in n downTo 1) {
            for (k in 0 until 2) {
                val ni = i + dirBx[k]
                val nj = j + dirBy[k]
                if (ni in 1..n && nj in 1..m) {
                    dyB[ni][nj] = max(dyB[ni][nj], dyB[i][j] + board[ni][nj])
                }
            }

        }
    }

//    for (i in 1..n) {
//        for (j in 1..m) {
//            print("${dyB[i][j]} ")
//        }
//        println()
//    }

    var ans = -987654321
    for (i in 1..n) {
        for (j in 1..m) {
            ans = max(ans, dyA[i][j] + dyB[i][j])
        }
    }
    println(ans)
}