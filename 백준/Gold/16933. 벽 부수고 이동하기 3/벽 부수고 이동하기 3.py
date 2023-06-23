from collections import deque
import sys

si = sys.stdin.readline

# dxs = [-1, 0, 1, 0]
# dys = [0, -1, 0, 1]

dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]

INT_MAX = sys.maxsize

n, m, k = map(int, si().split())
board = [list(map(int, si().strip())) for _ in range(n)]
visited = [[[INT_MAX] * (k + 1) for _ in range(m)] for _ in range(n)]
# 낮 1, 밤 0
q = deque([(0, 0, 1, k)])

result = INT_MAX

while q:
    x, y, t, left = q.popleft()

    if (x, y) == (n - 1, m - 1):
        result = min(result, t)
        continue

    daytime = t % 2
    for k in range(4):
        nx, ny = x + dx[k], y + dy[k]
        if 0 <= nx < n and 0 <= ny < m:
            if board[nx][ny] == 0 and visited[nx][ny][left] > t:
                visited[nx][ny][left] = t
                q.append((nx, ny, t + 1, left))
            if board[nx][ny] == 1 and left and visited[nx][ny][left - 1] > t:
                if daytime:
                    visited[nx][ny][left - 1] = t
                    q.append((nx, ny, t + 1, left - 1))
                else:
                    q.append((x, y, t + 1, left))

print(result if result != INT_MAX else -1)
