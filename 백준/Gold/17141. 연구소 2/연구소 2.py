import sys
from collections import deque
from itertools import combinations

si = sys.stdin.readline

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

n, m = map(int, si().split())
virus = []

board = [list(map(int, si().split())) for _ in range(n)]

for i in range(n):
    for j in range(n):
        if board[i][j] == 2:
            virus.append((i, j))


def in_range(x, y):
    return 0 <= x < n and 0 <= y < n


def bfs(viruses):
    dist = [[-1] * n for _ in range(n)]
    q = deque(viruses)
    for x, y in viruses:
        dist[x][y] = 0

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if in_range(nx, ny) and board[nx][ny] != 1 and dist[nx][ny] == -1:
                dist[nx][ny] = dist[x][y] + 1
                q.append((nx, ny))

    return dist


min_value = sys.maxsize
for com in combinations(virus, m):
    dist = bfs(com)
    max_value = 0
    flag = True
    for i in range(n):
        for j in range(n):
            if board[i][j] != 1 and dist[i][j] == -1:
                flag = False
                break
            else:
                max_value = max(max_value, dist[i][j])
        if not flag:
            break
    if flag:
        min_value = min(min_value, max_value)

print(min_value if min_value != sys.maxsize else -1)
