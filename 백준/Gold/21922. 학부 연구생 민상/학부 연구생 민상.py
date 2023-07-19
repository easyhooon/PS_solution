from collections import deque
import sys

# 굳이 방문 배열을 3차원으로 관리하지 않아도 된다.

si = sys.stdin.readline

n, m = map(int, si().split())
visited = [[0] * m for _ in range(n)]

q = deque()
drs = [-1, 1, 0, 0]
dcs = [0, 0, -1, 1]

graph = []
for i in range(n):
    line = list(map(int, si().split()))
    for j in range(m):
        if line[j] == 9:
            q.append((i, j))
            visited[i][j] = 1
    graph.append(line)


def bfs():
    while q:
        cr, cc = q.popleft()
        for idx in range(4):
            nr, nc = drs[idx], dcs[idx]
            r, c = cr + nr, cc + nc
            while 0 <= r < n and 0 <= c < m:
                visited[r][c] = 1
                if graph[r][c] == 9:
                    break
                if graph[r][c] == 3:
                    nr, nc = -nc, -nr
                elif graph[r][c] == 4:
                    nr, nc = nc, nr
                elif (graph[r][c] == 1 and nr == 0) or (graph[r][c] == 2 and nc == 0):
                    break
                r += nr
                c += nc
    answer = 0
    for ans in visited:
        answer += ans.count(1)
    return answer


print(bfs())
