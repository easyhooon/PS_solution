class Solution {
    fun solution(n: Int, m: Int) = intArrayOf(gcd(n, m), lcm(n, m))

    fun lcm(n: Int, m: Int) = n * m / gcd(n, m)

    fun gcd(n: Int, m: Int): Int {
        return if (n < m) {
            if (n == 0) m else gcd(n, m % n)
        } else {
            if (m == 0) n else gcd(m, n % m)
        }
    }
}