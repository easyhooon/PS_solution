import sys
import heapq

si = sys.stdin.readline

n = int(si())
board = []
for _ in range(n):
    board.append(list(map(int, si().strip())))

dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]

# 방문 여부 체크를 위한 visited 2차원 리스트
visited = [[False] * n for _ in range(n)]


def in_range(nx, ny):
    return 0 <= nx < n and 0 <= ny < n


def bfs(x, y):
    heap = []
    heapq.heappush(heap, (0, x, y))

    while heap:
        cnt, x, y = heapq.heappop(heap)
        visited[x][y] = True

        if x == (n - 1) and y == (n - 1):
            return cnt

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if in_range(nx, ny) and not visited[nx][ny]:
                visited[nx][ny] = True
                # 흰 방
                if board[nx][ny] == 1:
                    heapq.heappush(heap, (cnt, nx, ny))
                # 검은 방
                else:
                    heapq.heappush(heap, (cnt + 1, nx, ny))


print(bfs(0, 0))
