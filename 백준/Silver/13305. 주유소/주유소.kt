val sb = StringBuilder()

// 주유소 - 그리디 알고리즘
// 1번째는 무조건 1번째 도로만큼의 가격을 지불해야한다
// 2번째 주유소 가격이 더 낮으면 1번은 1번째 도로만큼만 지불하고 그 다음부턴 2번째 주유소에서 해결

// 파이썬이 좋은점 
lateinit var dist: LongArray
lateinit var price: LongArray
var result = 0L

fun main() {
    val N = readln().toInt()
    dist = LongArray(N-1)
    price = LongArray(N)

    dist = readln().split(" ").map { it.toLong() }.toLongArray()
    price = readln().split(" ").map { it.toLong() }.toLongArray()

    solution(N)
}

fun solution(N: Int) {
    var p = price[0]
    for(i in 0 until N-1) {
        if (p > price[i]) {
            p = price[i]
        }
        result += p * dist[i]
    }
    println(result)
}


