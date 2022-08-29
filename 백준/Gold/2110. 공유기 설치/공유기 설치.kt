//뒤집은 문제: 인접 거리를 mid 로 했을때 공유기를 n개 설치할 수 있나?
import java.util.*

val sb = StringBuilder()
var ans: Int = 0
lateinit var A: IntArray

fun main() {
    val (N, C) = readln().split(" ").map { it.toInt()}

    A = IntArray(N + 1)

    for (i in 1..N) {
        A[i] = readln().toInt()
    }

    Arrays.sort(A, 1, N+1)

    ans = solve(N, C)

    sb.append(ans)
    print(sb.toString())
}

fun solve(N: Int, C: Int): Int {
    var L = 1
    var R = A[N-1]
    while (L <= R) {
        val mid = ((L + R) / 2)
        if (determination(N, C, mid)) {
            // mid 아래는 어차피 다 x보다 작음 -> L 을 땡겨와야 함
            ans = mid
            L = mid + 1
        } else {
            // mid 보다 큰 건 어차피 다 x보다 큼 -> R 을 땡겨와야 함
            R = mid - 1
        }
    }
    return ans
}


// 인접거리 mid 에 공유기를 M개를 설치할 수 있는가
// 최대거리를 구하는 것이므로 양쪽 끝에 공유기 2개는 고정으로 박는게? ㄴㄴ 왼쪽에만
fun determination(N: Int, C: Int, mid: Int): Boolean {
    //다 세워본다
    var cnt = 1
    var last = A[1]
    for(i in 2..N) {
        if(A[i] - last < mid) continue
        last = A[i]
        cnt++
    }

    return cnt >= C
}