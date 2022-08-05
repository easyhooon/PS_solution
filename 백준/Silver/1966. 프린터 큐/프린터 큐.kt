import java.util.*

// 프린터 큐

val sb = StringBuilder()

lateinit var A: ArrayList<Int>

//중요도가 더 높은 문서가 존재하면 보다 낮은 문서들을 pop 할 수 없다.
//중요도가 더 높은 문서가 존재한다는 판단을 어떻게 해야할 것인가.

fun main() {
    val tc = readln().toInt()
    for(i in 1..tc) {
        val (N,M) = readln().split(" ").map { it.toInt() }
        A = ArrayList()
        val input = readln().split(" ")
        //println(input.joinToString())
        for(i in 0 until N) {
            A.add(input[i].toInt())
        }
        solution(N, M)
    }
}

// 인덱스와 숫자를 같이 저장하면 쉽네
fun solution(N: Int, M: Int) {
    val q: Queue<Pair<Int,Int>> = LinkedList()
    // 큐에 넣어주기 전에 조건,
    // 일단 다넣고 빼면서 조건
    // 정렬하면 같은 우선순위인 경우에 판단을 할 수 없다.
    for(i in A.indices) {
        q.offer(Pair(i,A[i]))
    }
    var cnt = 0
    while(q.isNotEmpty()) {
        val t = q.poll()
        if(t.second == A.maxOrNull()) {
            cnt++
            if(t.first == M) {
                println(cnt)
                return
            }
            A.remove(A.maxOrNull())
            //println(A.joinToString())
        }
        else {
            q.offer(t)
        }
    }
}
