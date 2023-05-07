import sys
sys.setrecursionlimit(10**6)

si = sys.stdin.readline

n, m = map(int, si().split())

uf = [0] * (n + 1)
edges = []
mst = []
for i in range(1, n + 1):
    uf[i] = i

for _ in range(m):
    a, b, c = map(int, si().split())
    edges.append((a, b, c))

edges.sort(key=lambda x: (x[2]))


def find(x):
    if uf[x] == x:
        return x

    root_node = find(uf[x])
    uf[x] = root_node

    return root_node


def union(x, y):
    x = find(x)
    y = find(y)

    uf[x] = y


for u, v, w in edges:
    if find(u) != find(v):
        mst.append((u, v, w))
        union(u, v)

answer = 0

# print(mst)

for _, _, w in mst:
    answer += w

# mst 에서 하나만 더 쳐내면 두개의 마을로 분리되는 것이므로
# 가장 가중치가 큰 간선을 쳐내면 최소 유지비의 간선을 통해 이루어지는 두 개의 마을이 만들어진다.
answer -= mst[-1][2]

print(answer)
