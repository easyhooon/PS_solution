import java.util.*

val sb = StringBuilder()
val answer = ArrayList<Int>()

fun main() {
    val (N,M) = readln().split(" ").map { it.toInt() }
    solution(N, M)

    println("<${answer.joinToString()}>")

//    sb.apply {
//        append("<")
//        for(i in 0 until N-1) {
//            append("${answer[i]}, ")
//        }
//        append(answer[N-1])
//        append(">")
//    }
}

fun solution(N:Int, M: Int) {
    val q: Queue<Int> = LinkedList()
    for(i in 1..N) {
        q.offer(i)
    }

    while(q.isNotEmpty()) {
        for(i in 1 until M) {
            q.offer(q.poll())
        }
        val num = q.poll()
        answer.add(num)
    }
}
