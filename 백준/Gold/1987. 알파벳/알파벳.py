# 최장거리 -> 백트래킹을 통한 완전 탐색
import sys

si = sys.stdin.readline

n, m = map(int, si().split())
a = [input() for _ in range(n)]
dirs = ((1, 0), (0, 1), (-1, 0), (0, -1))
ans, used = 0, [False for _ in range(26)]  # 정답 변수 & 사용한 알파벳 기록 배열


def backtracking(x: int, y: int, cnt: int):  # 현재 위치 (x, y) 와 이동 거리 (cnt)를 가지고 남은 모든 경우를 탐색해주는 함수
    global ans
    ans = max(ans, cnt)
    used[ord(a[x][y]) - ord('A')] = True

    for dx, dy in dirs:
        nx, ny = x + dx, y + dy
        if 0 <= nx < n and 0 <= ny < m and not used[ord(a[nx][ny]) - ord('A')]:
            backtracking(nx, ny, cnt + 1)

    used[ord(a[x][y]) - ord('A')] = False


backtracking(0, 0, 1)
print(ans)