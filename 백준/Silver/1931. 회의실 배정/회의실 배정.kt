//회의실 배정 - 그리디

val sb = StringBuilder()

lateinit var A: ArrayList<Pair<Int, Int>>
lateinit var dy: Array<IntArray> // i 시간 까지 도달하였을때 가장 많은 회의를 한 횟 수 j

fun main() {
    val N = readln().toInt()
    A = ArrayList<Pair<Int, Int>>()
    repeat(N) {
        val (start, end) = readln().split(" ").map { it.toInt() }
        A.add(Pair(start, end))
    }

    val ans = solution(N)
    sb.append(ans).append("\n")
    print(sb.toString())
}


fun solution(N: Int): Int {
    val sortedA = A.sortedWith(compareBy({ it.second }, { it.first }))

    //println(sortedA.joinToString())

    var cnt = 0
    var beforeEndTime = 0
    for (i in 0 until N) {
        val startTime = sortedA[i].first
        val endTime = sortedA[i].second
        if(startTime >= beforeEndTime) {
            cnt++
            beforeEndTime = endTime
        }
    }
    return cnt
}



