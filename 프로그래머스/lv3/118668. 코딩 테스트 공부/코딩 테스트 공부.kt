import kotlin.math.min

class Solution {
    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        // 문제 중 최대 알고력
        val maxAlp = problems.maxOf { it[0] }
        // 문제 중 최대 코딩력
        val maxCop = problems.maxOf { it[1] }

//         println(maxAlp)
//         println(maxCop)

        val dp = Array(maxAlp + 1) { IntArray(maxCop + 1) { Int.MAX_VALUE } }

        val targetAlp = min(alp, maxAlp)
        val targetCop = min(cop, maxCop)

        // 기저 조건
        dp[targetAlp][targetCop] = 0

        for (i in targetAlp until maxAlp + 1) {
            for (j in targetCop until maxCop + 1) {
                if (i + 1 <= maxAlp) {
                    dp[i + 1][j] = min(dp[i + 1][j], dp[i][j] + 1)
                }

                if (j + 1 <= maxCop) {
                    dp[i][j + 1] = min(dp[i][j + 1], dp[i][j] + 1)
                }

                for (problem in problems) {
//                    if (i >= problem[0] && j >= problem[1] && i + problem[2] <= maxAlp && j + problem[3] <= maxCop) {
//                        dp[i + problem[2]][j + problem[3]] = min(dp[i + problem[2]][j + problem[3]], dp[i][j] + problem[4])
//                    }
                    if (i >= problem[0] && j >= problem[1]) {
                        val nextAlp = min(maxAlp, i + problem[2])
                        val nextCop = min(maxCop, j + problem[3])
                        dp[nextAlp][nextCop] = min(dp[nextAlp][nextCop], dp[i][j] + problem[4])
                    }
                }
            }
        }

        return dp[maxAlp][maxCop]
    }
}

