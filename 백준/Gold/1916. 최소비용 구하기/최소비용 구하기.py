import sys
import heapq

si = sys.stdin.readline


def dijkstra(start, dist, edges):
    pq = []
    heapq.heappush(pq, (0, start))
    dist[start] = 0

    while pq:
        current_dist, idx = heapq.heappop(pq)

        if dist[idx] != current_dist:
            continue

        for to, weight in edges[idx]:
            if current_dist + weight >= dist[to]:
                continue

            dist[to] = current_dist + weight
            heapq.heappush(pq, (dist[to], to))

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
