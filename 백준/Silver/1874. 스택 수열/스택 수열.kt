import java.io.*
import java.util.*

val sb = StringBuilder()
val s = Stack<Int>()
val answer = ArrayList<Char>()

var N = 0
lateinit var A: IntArray
lateinit var check: BooleanArray

fun main() {
    input()
    val ans = pro()

    if(ans) {
        for(i in answer.indices) {
            println(answer[i])
        }
    } else {
        println("NO")
    }
}

fun input() {
    N = readln().toInt()
    A = IntArray(N+1)
    check = BooleanArray(N+1)
    for (i in 1..N) {
        A[i] = readln().toInt()
    }
}

fun pro(): Boolean {
    for(i in 1..A[1]) {
        s.push(i)
        answer.add('+')
    }

    var cnt = 1

    while(s.isNotEmpty()) {
        val t = s.pop()
        check[t] = true
        //println(t)
        if(t == A[cnt]) {
            cnt++
            answer.add('-')
        }

        if(cnt == N+1) {
            return true
        }

        if(t < A[cnt]) {
            for(i in t+1..A[cnt]) {
                if(check[i]) continue
                //println("$i push")
                s.push(i)
                answer.add('+')
            }
        }
    }

    return false
}