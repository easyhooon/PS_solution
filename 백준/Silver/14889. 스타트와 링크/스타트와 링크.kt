import kotlin.math.abs
import kotlin.math.min

//너무 더럽게 푼다
//-> 문제 이해를 하지 못하였기 때문에

// n/2 개를 고르는 것이 끝이 아님, 고르고 나서 서로 짝을 지어야함
// 순서가 정해져 있음 -> 순열 (1, 2, 4, 5) 를 골랐다면 앞에서 부터 (1, 2), (4, 5) 가 한쌍, 나머지 (3, 6), (7,8)이 한쌍

val sb = StringBuilder()
var N = 0
var minDiff = Int.MAX_VALUE

lateinit var board: Array<IntArray>
lateinit var selected: IntArray
lateinit var unselected: ArrayList<Int>

fun main() {
    N = readln().toInt()
    board = Array(N + 1) { IntArray(N + 1) }
    selected = IntArray(N / 2 + 1)

    for (i in 1..N) {
        val input = readln().split(" ")
        for (j in 1..N) {
            board[i][j] = input[j - 1].toInt()
        }
    }

    //완탐 차이 최솟값 갱신
    solution(1)

    sb.append(minDiff).append("\n")
    print(sb.toString())
}

// selected 된 갯수가 홀 수 일 수도 있다. 6 10 ....
// 홀수일 경우엔 ex) i j k -> ij ji ik ki jk kj 를 모두 더하는 형식이다.

fun solution(k: Int) {
    if (k == N / 2 + 1) {
//        for (i in 1..N / 2) {
//            print("${selected[i]} ")
//        }
//        println()

        unselected = ArrayList()
        unselected.add(0)

        for (i in 1..N) {
            if (selected.contains(i)) continue
            unselected.add(i)
        }

//        for (i in 1..N / 2) {
//            print("${unselected[i]} ")
//        }
//        println("\n")

        calculate()
    } else {
        for (cand in 1..N) {
            selected[k] = cand
            if (selected[k - 1] < selected[k]) {
                solution(k + 1)
                selected[k] = 0
            }
        }
    }
}

// pair로 짜는거 아님 전체 합
fun calculate() {
    var selected_sum = 0
    for (i in 1..N / 2 - 1) {
        for (j in i + 1..N / 2) {
            val x = selected[i]
            val y = selected[j]
            selected_sum += board[x][y] + board[y][x]
        }
    }

    var unselected_sum = 0
    for (i in 1..N / 2 - 1) {
        for (j in i + 1..N / 2) {
            val x = unselected[i]
            val y = unselected[j]
            unselected_sum += board[x][y] + board[y][x]
        }
    }
//    println(selected_sum)
//    println(unselected_sum)

    minDiff = min(minDiff, abs(selected_sum - unselected_sum))
}


