# 벽부이2

import sys
from collections import deque

si = sys.stdin.readline

q = deque()
dxs = [-1, 0, 1, 0]
dys = [0, -1, 0, 1]

n, m, k = map(int, si().split())

temp = [list(si().strip()) for _ in range(n)]
graph = [[0] * m for _ in range(n)]
dist = [[[-1] * (k + 1) for _ in range(m)] for _ in range(n)]

for i in range(n):
    for j in range(m):
        if temp[i][j] == '0':
            graph[i][j] = 0
        else:
            graph[i][j] = 1


# 시간 초과
# def get_connection(x, y, cnt):
#     con = []
#     for dx, dy in zip(dxs, dys):
#         nx = x + dx
#         ny = y + dy
#         if in_range(nx, ny):
#             if graph[nx][ny] == 0:
#                 con.append((nx, ny, cnt))
#             else:
#                 if cnt + 1 <= k:
#                     con.append((nx, ny, cnt + 1))
#
#     return con


def in_range(x, y):
    return 0 <= x < n and 0 <= y < m


def bfs():
    q.append((0, 0, 0))
    dist[0][0][0] = 1

    while q:
        x, y, cnt = q.popleft()

        # for next_x, next_y, next_cnt in get_connection(x, y, cnt):
        #     if dist[next_x][next_y][next_cnt] == -1:
        #         dist[next_x][next_y][next_cnt] = dist[x][y][cnt] + 1
        #         q.append((next_x, next_y, next_cnt))

        for dx, dy in zip(dxs, dys):
            nx = x + dx
            ny = y + dy
            if in_range(nx, ny):
                # 벽이 아닌 경우
                if graph[nx][ny] == 0:
                    if dist[nx][ny][cnt] == -1:
                        dist[nx][ny][cnt] = dist[x][y][cnt] + 1
                        q.append((nx, ny, cnt))
                # 벽일 경우
                else:
                    if cnt + 1 <= k and dist[nx][ny][cnt + 1] == -1:
                        dist[nx][ny][cnt + 1] = dist[x][y][cnt] + 1
                        q.append((nx, ny, cnt + 1))


bfs()

answer = sys.maxsize

for cnt in range(k + 1):
    if dist[n - 1][m - 1][cnt] != -1:
        answer = min(answer, dist[n - 1][m - 1][cnt])

if answer == sys.maxsize:
    print(-1)
else:
    print(answer)
