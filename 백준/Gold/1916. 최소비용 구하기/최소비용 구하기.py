# 최소 비용 구하기 2

# 최소 비용
# 최소 비용을 갖는 경로에 포함되어있는 도시의 개수(출발, 도착 도시 포함
# 경로를 방문하는 도시 순서대로 출력

import sys
import heapq

si = sys.stdin.readline


def dijkstra(start, dist, edges):
    pq = []
    heapq.heappush(pq, (0, start))
    dist[start] = 0

    while pq:
        min_dist, min_idx = heapq.heappop(pq)

        if dist[min_idx] != min_dist:
            continue

        for target_idx, target_dist in edges[min_idx]:
            new_dist = dist[min_idx] + target_dist
            if dist[target_idx] > new_dist:
                dist[target_idx] = new_dist
                heapq.heappush(pq, (new_dist, target_idx))

    return dist


n = int(si())
m = int(si())

dist = [sys.maxsize for _ in range(n + 1)]
edges = [[] for _ in range(n + 1)]

for _ in range(m):
    x, y, w = map(int, si().split())
    edges[x].append((y, w))

# 출발점의 도시번호, 도착점의 도시번호가 주어짐 -> 다익스트라
start, end = map(int, si().split())

dist = dijkstra(start, dist, edges)
print(dist[end])
