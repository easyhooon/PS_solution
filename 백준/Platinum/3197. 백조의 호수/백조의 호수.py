# 2차원 유니온 파인드 푸는 방법
# 1. 백조가 만날 수 있는지 여부를 매번 탐색하지 않는다.
# 2. 녹을 가장 자리의 위치를 미리 어딘가에 지정하자. 예상이 가능하다.
# 얼음 X, 물 . 백조 L

import sys
from collections import deque

si = sys.stdin.readline

dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]

r, c = map(int, si().split())
board = [list(si().strip()) for _ in range(r)]
root = [[(i, j) for j in range(c)] for i in range(r)]
rank = [[0 for j in range(c)] for i in range(r)]
visited = [[False for _ in range(c)] for _ in range(r)]
swan = []

for i in range(r):
    for j in range(c):
        if board[i][j] == "L":
            swan.append((i, j))
            board[i][j] = "."
            if len(swan) == 2:
                break


def in_range(x, y):
    return 0 <= x < r and 0 <= y < c


def find(v):
    if v == root[v[0]][v[1]]:
        return v
    root[v[0]][v[1]] = find(root[v[0]][v[1]])
    return root[v[0]][v[1]]


def union(v1, v2):
    r1 = find(v1)
    r2 = find(v2)

    if rank[r1[0]][r1[1]] > rank[r2[0]][r2[1]]:
        root[r2[0]][r2[1]] = r1
    elif rank[r1[0]][r1[1]] < rank[r2[0]][r2[1]]:
        root[r1[0]][r1[1]] = r2
    else:
        root[r2[0]][r2[1]] = r1
        rank[r1[0]][r1[1]] += 1


melt = deque()

for i in range(r):
    for j in range(c):
        if not visited[i][j] and board[i][j] == ".":
            q = deque()
            q.append((i, j))
            visited[i][j] = True
            while q:
                x, y = q.popleft()
                root[x][y] = (i, j)
                for k in range(4):
                    nx, ny = x + dx[k], y + dy[k]
                    if in_range(nx, ny) and not visited[nx][ny] and board[nx][ny] == ".":
                        visited[nx][ny] = True
                        q.append((nx, ny))
                    elif in_range(nx, ny) and not visited[nx][ny] and board[nx][ny] == "X":
                        visited[nx][ny] = True
                        melt.append((nx, ny))

# 시뮬레이션 시작
day = 0
# 백조가 만날 수 있는지 여부는 두 백조가 같은 루트를 갖는지로 판단
while find(swan[0]) != find(swan[1]):
    # 각 날짜 마다, 'melt'에 있는 얼음을 녹이고, 주변 얼음을 'tmp'에 추가
    tmp = deque()
    while melt:
        x, y = melt.popleft()
        board[x][y] = "."
        merge_point = []
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if in_range(nx, ny) and not visited[nx][ny] and board[nx][ny] == "X":
                visited[nx][ny] = True
                tmp.append((nx, ny))
            elif in_range(nx, ny) and board[nx][ny] == ".":
                merge_point.append((nx, ny))

        for rt in merge_point:
            if find(rt) != find((x, y)):
                union(rt, (x, y))

    melt = tmp
    day += 1

# 두 백조가 같은 집합에 속하게 되는 날짜를 출력
print(day)
