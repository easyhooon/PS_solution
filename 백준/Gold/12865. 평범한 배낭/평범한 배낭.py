import sys
from pprint import pprint


def si():
    return sys.stdin.readline().rstrip()

def preprocess():
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            if weight[i] > j:
                dp[i][j] = dp[i-1][j]
            else:
                dp[i][j] = max(dp[i-1][j], dp[i-1][j - weight[i]] + value[i])


n, m = map(int, si().split())
value = [0] + [0 for _ in range(n)]
weight = [0] + [0 for _ in range(n)]

for i in range(1, n + 1):
    w, v = map(int, si().split())
    weight[i] = w
    value[i] = v

dp = [[0 for _ in range(m + 1)] for _ in range(n + 1)]

preprocess()

# print(dp)
print(dp[n][m])