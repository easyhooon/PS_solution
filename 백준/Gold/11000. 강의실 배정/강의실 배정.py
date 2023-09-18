import sys
import heapq

si = sys.stdin.readline

n = int(si())

arr = []
for _ in range(n):
    start, end = map(int, si().split())
    arr.append((start, end))

arr.sort(key=lambda x: x[0])

pq = []

for start, end in arr:
    if pq and pq[0] <= start:
        heapq.heappop(pq)
    heapq.heappush(pq, end)

print(len(pq))
