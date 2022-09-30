import sys
sys.setrecursionlimit(10**6)


def si():
    return sys.stdin.readline().rstrip()


def dfs(start):
    global cnt
    ans[start] = cnt
    cnt += 1

    for elem in graph[start]:
        if not visited[elem]:
            visited[elem] = True
            dfs(elem)


n, m, r = map(int, si().split())
graph = [[] for _ in range(n + 1)]
visited = [False for _ in range(n + 1)]
ans = [0 for _ in range(n + 1)]
cnt = 1


for _ in range(m):
    x, y = map(int, si().split())
    graph[x].append(y)
    graph[y].append(x)

for i in range(n):
    graph[i].sort()

# print(graph)

visited[r] = True
dfs(r)

# print(ans)

# print(visited)

for i in range(1, n + 1):
    print(ans[i])
