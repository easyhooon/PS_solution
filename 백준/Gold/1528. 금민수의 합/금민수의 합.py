import sys
from collections import deque

si = sys.stdin.readline


# 1,000,000보다 작은 모든 금민수 생성
def generate_gms():
    q = deque(['4', '7'])
    gms = []

    while q:
        num = q.popleft()
        int_num = int(num)

        if int_num > 1000000:  # 주어진 범위를 초과한 경우
            break

        gms.append(int_num)

        q.append(num + '4')
        q.append(num + '7')

    return gms


# BFS를 통해 주어진 N을 금민수의 합으로 나타내는 함수
def bfs(n, gms):
    queue = deque([(n, [])])
    visited = set()
    visited.add(n)

    while queue:
        current, path = queue.popleft()

        if current == 0:
            return sorted(path)

        for g in gms:
            next_val = current - g
            if next_val >= 0 and next_val not in visited:
                visited.add(next_val)
                queue.append((next_val, path + [g]))

    return None


n = int(si())
gms = generate_gms()
result = bfs(n, gms)

if result:
    for r in result:
        print(r, end=" ")
else:
    print(-1)
