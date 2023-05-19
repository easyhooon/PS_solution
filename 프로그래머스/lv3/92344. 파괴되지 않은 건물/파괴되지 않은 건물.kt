class Solution {
    fun accumulate(board: Array<IntArray>) {
        for (i in 0 until board.size) {
            for (j in 1 until board[0].size) {
                board[i][j] += board[i][j - 1]
            }
        }

        for (i in 1 until board.size) {
            for (j in 0 until board[0].size) {
                board[i][j] += board[i - 1][j]
            }
        }
    }

    fun pointScore(board: Array<IntArray>, r1: Int, c1: Int, r2: Int, c2: Int, score: Int) {
        board[r1][c1] += score
        if(c2 + 1 < board[0].size) board[r1][c2 + 1] -= score
        if(r2 + 1 < board.size) board[r2 + 1][c1] -= score
        // 두번 빼줬기 때문에 다시 한번 더해줌
        if(c2 + 1 < board[0].size && r2 + 1 < board.size) board[r2 + 1][c2 + 1] += score
    }

    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
        var answer = 0
        val accumulatedBoard = Array(1001) { IntArray(1001) { 0 } }

        for (s in skill) {
            var score = s[5]
            if (s[0] == 1) {
                score = -s[5]
            }
            pointScore(accumulatedBoard, s[1], s[2], s[3], s[4], score)
        }
        accumulate(accumulatedBoard)

        for (i in 0 until board.size) {
            for (j in 0 until board[0].size) {
                board[i][j] += accumulatedBoard[i][j]
                if (board[i][j] > 0) {
                    answer += 1
                }
            }
        }

        return answer
    }
}