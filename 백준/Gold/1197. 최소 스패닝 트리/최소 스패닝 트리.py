import sys

si = sys.stdin.readline

n, m = map(int, si().split())
# graph = [[] for _ in range(n + 1)]
edges = []
uf = [0] * (n + 1)
mst = []

for _ in range(m):
    x, y, w = map(int, si().split())
    # graph[x].append((y, w))
    # graph[y].append((x, w))
    edges.append((x, y, w))

# 간선을 가중치 기준으로 오름차순 정렬
sorted_edges = sorted(edges, key = lambda x: (x[2]))
# print(sorted_edges)


def find(x):
    # x 가 루트 노드 라면 
    if uf[x] == x:
        # x 값을 반환 
        return x

    # x 가 루트 노드가 아니라면, x 의 부모인 uf[x]에서 더 탐색을 진행
    root_node = find(uf[x])
    # 노드 x 에 부모를 루트 노드로 설정
    uf[x] = root_node
    # 찾아낸 루트 노드를 반환 
    return root_node


def union(x, y):
    x = find(x)
    y = find(y)
    uf[x] = y


# uf 배열을 노드의 수 |V| 만큼 초기화 
for i in range(1, n + 1):
    uf[i] = i

# 각 간선에 대해서 간선을 이루고 있는 두 노드 u,v 를 보며
for u, v, w in sorted_edges:
    # u, v 의 루트 노드가 다른 경우에만
    if find(u) != find(v):
        # mst에 해당 간선을 넣어주고 
        mst.append((u, v, w))
        # u, v 를 같은 루트 노드를 갖도록 만들어 줌
        union(u, v)

answer = 0

# print(mst)

for _, _, w in mst:
    answer += w 

print(answer)

