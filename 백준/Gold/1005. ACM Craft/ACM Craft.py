from collections import deque
import sys

si = sys.stdin.readline

tc = int(si())

# 테스트 케이스가 존재하는 문제이므로 "배열 초기화"에 유의하자
for _ in range(tc):
    n, m = map(int, si().split())
    indegree = [0] * (n + 1)
    t = [0] + list(map(int, si().split()))

    t_done = t.copy()

    # 각 정점에 대해서 인접 리스트 생성
    adj = [[] for _ in range(n + 1)]

    # 간선 입력받기
    # V = E + 1
    for _ in range(m):
        x, y = map(int, si().split())
        # 위상정렬은 방향성이 있는 그래프이므로 한쪽으로만 이동가능, 한쪽만 연결
        adj[x].append(y)
        # indegree 계산하기
        indegree[y] += 1

    # 승리하기 위해 건설해야 할 건물 번호 W
    w = int(si())

    q = deque()
    # 제일 앞에 "정렬될 수 있는" 정점 찾기
    for i in range(1, n + 1):
        if indegree[i] == 0:
            q.append(i)

    # 정렬될 수 있는 정점이 있다면?
    # 1. 정렬 결과에 추가하기
    # 2. 정점과 연결된 간선 제거하기
    # 3. 새롭게 "정렬 될 수 있는" 정점 Queue 에 추가하기
    while q:
        x = q.popleft()
        for y in adj[x]:
            indegree[y] -= 1
            # 더 큰 값으로 갱신
            t_done[y] = max(t_done[y], t_done[x] + t[y])
            # 차수가 0으로 들어오는 순간 Queue 에 추가하기
            if indegree[y] == 0:
                q.append(y)

    print(t_done[w])
