import sys

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]
INF = sys.maxsize
answer = INF


def solution(board):
    n = len(board)
    # x, y 칸에 도착했을때 최소 비용
    dp = [[INF] * n for _ in range(n)]

    # 파이썬은 함수안에 함수를 선언할 수 도 있음
    def find_min_cost(x, y, n, cost, dir):
        global answer

        if dp[x][y] < cost:
            return
        else:
            dp[x][y] = cost

        if (x, y) == (n - 1, n - 1):
            answer = min(answer, cost)
            return

        # 지나온 곳을 다시 방문하지 않도록
        # 1은 벽
        board[x][y] = -1
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < n and 0 <= ny < n and board[nx][ny] == 0:
                if (x, y) == (0, 0) or (dir + i) % 2 == 0:
                    find_min_cost(nx, ny, n, cost + 100, i)
                else:
                    find_min_cost(nx, ny, n, cost + 600, i)
        board[x][y] = 0

    find_min_cost(0, 0, len(board), 0, 0)

    return answer
