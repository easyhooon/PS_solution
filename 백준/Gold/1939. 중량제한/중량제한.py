# 중량 제한

# 섬이 있고, 몇 개의 섬 사이에는 다리가 설치 되어 있어 차로 지나갈 수 있음
# 각각의 다리에는 중량 제한이 존재
# 중량을 초과하면 다리가 무너짐
# 한번에 옮길 수 있는 물품들의 중량의 최댓값을 구하라
# "서로 같은 두 섬 사이에, 여러 개의 다리가 있을 수도 있음"
# 모든 다리는 양방향

# 3 3
# 1 2 2
# 3 1 3
# 2 3 2
# 1 3

# 3

import sys
from collections import deque

si = sys.stdin.readline


def is_possible(start, end, mid):
    q = deque()
    visited = [False] * (n + 1)
    visited[start] = True
    q.append(start)

    # bfs 수행
    while q:
        x = q.popleft()
        # 도착 지점에 도착
        if x == end:
            # mid 를 최대 중량 제한으로 설정 했을 때, end 지점에 도착할 수 있다.
            return True

        # 다리가 여러 개 일 수 있다.
        for y, weight in graph[x]:
            # 방문한 적이 없고, 다리를 건널 수 있는 경우 (다리의 중량 제한 >= 최대 중량 제한)
            # 초과하면 무너진다고 했으니까 같아도 됨
            if not visited[y] and weight >= mid:
                visited[y] = True
                q.append(y)

    # mid 를 최대 중량 제한으로 설정 했을 때, end 지점에 도착할 수 없다.
    return False


# n: 섬의 개수(2 <= n <= 10,000)
# m: 다리의 개수를 나타냄 (1 <= m <= 100,000)
n, m = map(int, si().split())
graph = [[] for _ in range(n + 1)]

for _ in range(m):
    # a 섬과 b 섬 사이에 중량 제한이 c 인 다리가 존재
    # 1 <= a, b <= n, 1 <= c <= 1,000,000,000 <- 존내 크다 -> 파라매트릭 서치를 생각
    # 문제를 다시 정의) mid 라는 내가 설정한 최대 중량 제한으로 x -> y 를 건너갈 수 있는가?
    a, b, c = map(int, si().split())
    graph[a].append((b, c))
    graph[b].append((a, c))

# 공장이 있는 두 섬을
# 한번의 이동에서 옮길 수 있는 물픔의 중량의 최댓값
x, y = map(int, si().split())

left, right = 1, 1_000_000_000
# 최댓값을 구하는 문제이므로
answer = -sys.maxsize

while left <= right:
    mid = (left + right) // 2
    # print(mid)

    if is_possible(x, y, mid):
        # 최대 중량을 구하는 문제 이므로
        answer = mid
        left = mid + 1
    else:
        right = mid - 1

print(answer)

