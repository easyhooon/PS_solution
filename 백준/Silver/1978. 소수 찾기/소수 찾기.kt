import kotlin.math.sqrt

//방문 배열을 통해 중복 계산 제거

var N = 0
lateinit var A: IntArray
lateinit var isPrime: BooleanArray

fun main() {
    input()
    pro()
}

fun input() {
    N = readln().toInt()
    A = IntArray(N+1)
    isPrime = BooleanArray(1000 + 1) { true }

    val list = readln().split(" ")
    for(i in 1..N) {
        A[i] = list[i-1].toInt()
    }

//    for(i in 1..N) {
//        print("${A[i]} ")
//    }
//    println()
}

fun pro() {
    val sqrtOfM = sqrt(1000.toDouble()).toInt()

    // 2의 배수, 3의 배수, 5의 배수 ... sqrt 의 배수를 걸러낸다.
    for (i in 2..sqrtOfM) { //(1)
        // 이미 지워진 경우
        if (!isPrime[i])
            continue
        // 소수의 배수 제외
        else {
            var j = 2
            while (i * j <= 1000) {
                if (isPrime[i * j]) {
                    isPrime[i * j] = false
                }
                j++
            }
        }
    }

    var cnt = 0
    for (i in 1..N) {
        if (A[i] == 1) continue
        if (isPrime[A[i]]) cnt+= 1
    }

    println(cnt)
}