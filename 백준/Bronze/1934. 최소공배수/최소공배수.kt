fun main() {
    val N = readln().toInt()
    for (i in 1..N) {
        val (A, B) = readln().split(" ").map { it.toInt() }
        println(solution(A, B))
    }
}

fun solution(A: Int, B: Int): Int {
    return lcm(A, B)
}

fun lcm(A: Int, B: Int): Int {
    return A * B / gcd(A, B)
}

fun gcd(A: Int, B: Int): Int {
    return if (A > B) {
        if (B == 0) A else gcd(B, A % B)
    } else {
        if (A == 0) B else gcd(A, B % A)
    }
}
