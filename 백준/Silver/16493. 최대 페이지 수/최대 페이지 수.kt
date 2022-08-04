import kotlin.math.max

// 남은 기간 N 1~200
// 챕터의 수 M 1~20

val sb = StringBuilder()

lateinit var price: IntArray
lateinit var weight: IntArray
val dy = Array(21) { IntArray(301) }

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }

    weight = IntArray(M + 1)
    price = IntArray(M + 1)
    for (i in 1..M) {
        val (w, p) = readln().split(" ").map { it.toInt() }
        weight[i] = w
        price[i] = p
    }

    solution(N, M)

    //show(N,M)

    sb.append(dy[M][N]).append('\n')
    print(sb.toString())
}

fun solution(N: Int, M: Int) {
    for (i in 1..M) {
        for (j in 1..N) {
            if (weight[i] > j) {
                dy[i][j] = dy[i - 1][j]
            } else {
                dy[i][j] = max(dy[i - 1][j - weight[i]] + price[i], dy[i - 1][j])
            }
        }
    }
}

fun show(N: Int, M: Int) {
    for(i in 1..M) {
        for(j in 1..N) {
            print("${dy[i][j]} ")
        }
        println()
    }
}
