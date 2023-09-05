# 숨박꼭질 dp 풀이
import sys

si = sys.stdin.readline
MAX = 100001
# dp[i] := n 번째 칸에서 i 번째 칸으로 이동할 수 있는 가장 빠른 경우의 수
dp = [0] * MAX

n, k = map(int, si().split())

# n 보다 k가 작을 경우
if n >= k:
    print(n - k)
    exit(0)

# 기저 조건
for i in range(n):
    dp[i] = n - i

# 점화식 채우기
for i in range(n + 1, k + 1):
    # i 번째 칸이 2의 배수인 경우
    if i % 2 == 0:
        # i // 2 에서 1초만에 이동 가능
        min_dist = dp[i // 2] + 1
    # 아닌 경우
    else:
        # 한 칸 이동 후 순간 이동한 것 중 작은 것으로 min_dist 를 설정
        min_dist = min(dp[(i + 1) // 2], dp[(i - 1) // 2]) + 2

    # min_dist 와 그냥 한 칸 이동한 것 중에 최소 값으로 갱신
    dp[i] = min(min_dist, dp[i - 1] + 1)

print(dp[k])
