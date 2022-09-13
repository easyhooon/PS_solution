# 가장 긴 감소하는 부분 수열

import sys


def si():
    return sys.stdin.readline().rstrip()


n = int(si())


a = [0] + list(reversed(list(map(int, input().split()))))

dp = [0 for _ in range(n + 1)]

for i in range(1, n + 1):
    for j in range(i):
        if a[j] < a[i]:
            dp[i] = max(dp[i], dp[j] + 1)

print(max(dp))