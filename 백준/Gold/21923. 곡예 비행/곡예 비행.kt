import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Math.max
import java.lang.StringBuilder
import java.util.*

var n = 0
var m = 0

lateinit var a: Array<IntArray>
lateinit var dy: Array<Array<IntArray>>

val br = BufferedReader(InputStreamReader(System.`in`))
val sb = StringBuilder()

const val UP = 0
const val DOWN = 1
const val INF = 987654321


fun main() {
    input()
    pro()
}

fun input() {
    var st = StringTokenizer(br.readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()

//    //python 처럼 입력받기
//    val (n, m) = br.readLine().split(' ').map { it.toInt() }

//    println("$n $m")

    a = Array(n + 1) { IntArray(m + 1) }
    dy = Array(n + 1) { Array(m + 1) { IntArray(2) } }

    for (i in 1..n) {
        st = StringTokenizer(br.readLine())
        for (j in 1..m) {
            a[i][j] = st.nextToken().toInt()
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
//            print("${a[i][j]} ")
//        }
//        println()
//    }
}

fun pro() {
    for (i in 1..n) {
        for (j in 1..m) {
            dy[i][j][UP] = -987654321
            dy[i][j][DOWN] = -987654321
        }
    }

    dy[n][1][UP] = a[n][1]

    sb.append(solve(n, m, DOWN))

    print(sb.toString())

//    for(i in 1..n) {
//        for(j in 1..m) {
//            print("${dy[i][j][UP]} ")
//        }
//        println()
//    }

//    for(i in 1..n) {
//        for(j in 1..m) {
//            print("${dy[i][j][DOWN]} ")
//        }
//        println()
//    }
}

// 이러한 코드 형식이 c++의 형식이라.. 다른 형식으로 변환해주어야함
fun solve(i: Int, j: Int, dir: Int): Int {
    if (i < 1 || i > n || j < 1 || j > m) return -INF;
    if (dy[i][j][dir] != -INF) return dy[i][j][dir];

    if (dir == UP) {
        dy[i][j][dir] = max(solve(i + 1, j, dir), solve(i, j - 1, dir)) + a[i][j];
    }
    else {
        dy[i][j][dir] = max(solve(i - 1, j, dir), solve(i, j - 1, dir)) + a[i][j];
        dy[i][j][dir] = max(dy[i][j][dir], solve(i, j, UP) + a[i][j]);
    }
    return dy[i][j][dir]
}