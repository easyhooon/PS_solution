import sys

si = sys.stdin.readline

t = int(si())

for _ in range(t):
    k = int(si())
    arr = list(map(int, si().split()))
    prefix_sum = [0] * (k + 1)
    dp = [[0] * (k + 1) for _ in range(k + 1)]

    for i in range(1, k + 1):
        prefix_sum[i] = prefix_sum[i - 1] + arr[i - 1]

    for x in range(2, k + 1):
        for i in range(1, k - x + 2):
            j = i + x - 1
            dp[i][j] = sys.maxsize
            for l in range(i, j):
                dp[i][j] = min(dp[i][j], dp[i][l] + dp[l + 1][j] + prefix_sum[j] - prefix_sum[i - 1])

    print(dp[1][k])
