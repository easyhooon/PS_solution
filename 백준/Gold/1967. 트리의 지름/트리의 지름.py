import sys
sys.setrecursionlimit(10**6)

si = sys.stdin.readline

MAX = 10_000

n = int(si())
node = [[] for _ in range(MAX + 1)]
visited = [False] * (MAX + 1)
max_len = 0
max_len_point = 0


def dfs(point, length):
    global max_len, max_len_point

    if visited[point]:
        return

    visited[point] = True
    if max_len < length:
        max_len = length
        max_len_point = point

    for i in range(len(node[point])):
        dfs(node[point][i][0], length + node[point][i][1])


for _ in range(n - 1):
    parent, child, length = map(int, si().split())
    node[parent].append((child, length))
    node[child].append((parent, length))

# print(node)

# 가장 멀리 있는 정점(max_len_point) 구하기
dfs(1, 0)

max_len = 0

visited = [False] * (MAX + 1)

# max_len_point 와 가장 멀리 있는 정점과의 거리 구하기
dfs(max_len_point, 0)

print(max_len)
