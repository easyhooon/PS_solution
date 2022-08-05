import kotlin.math.abs
import kotlin.math.min
// 가로수
// 모든 간격의 최대공약수를 구해주면 되는데

val sb = StringBuilder()

var N = 0
lateinit var input: IntArray
lateinit var A: IntArray

fun main() {
    N = readln().toInt()

    input = IntArray(N)
    for (i in 0 until N) {
        input[i] = readln().toInt()
    }
    val s = mutableSetOf<Int>()
    for(i in 0 until N-1) {
        s.add(input[i + 1] - input[i])
    }
    A = s.toIntArray()
    //println(A.joinToString())

    val answer = solution()
    sb.append(answer).append('\n')
    print(sb.toString())
}

fun gcd(n: Int, m: Int): Int {
    return if (n < m) {
        if (n == 0) m else gcd(n, m % n)
    } else {
        if (m == 0) n else gcd(m, n % m)
    }
}

fun gcdArray(A: IntArray): Int {
    var gcd = A[0]
    for(item in A) {
        gcd = gcd(gcd, item)
    }
    return gcd
}


fun solution(): Int {
    // 이거 min_diff 를 최대 공약수로 구해줘야 된다. 그런데 최대공약수를 구하는 방법을 두 수로 부터 얻는 방법 밖에 모른다.
    // N 은 10억 이하
    var min_diff = gcdArray(A)
    //println(min_diff)

    var cnt = 0

    // 출력 확인용 (남겨두면, 메모리 초과)
    //val colonnade = ArrayList<Int>()
    for (i in 0 until N-1) {
        var start = input[i]
        var end = input[i+1]

        while(start + min_diff < end) {
            start += min_diff
            //colonnade.add(start)
            cnt += 1
        }
    }

    //println(colonnade.joinToString())

    return cnt
}
