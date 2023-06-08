import sys

si = sys.stdin.readline

n, m, k = map(int, si().split())

arr = [[0] * 301 for _ in range(301)]
dp = [[-1] * 301 for _ in range(301)]

for _ in range(k):
    a, b, c = map(int, input().split())
    arr[a][b] = max(arr[a][b], c)


def solve(idx, cnt):
    if idx != 1 and cnt == 1:
        return -sys.maxsize

    if idx == 1:
        return 0

    if dp[idx][cnt] != -1:
        return dp[idx][cnt]

    dp[idx][cnt] = -sys.maxsize

    for i in range(1, idx):
        if arr[i][idx]:
            dp[idx][cnt] = max(dp[idx][cnt], solve(i, cnt - 1) + arr[i][idx])

    return dp[idx][cnt]


ans = solve(n, m)

print(ans)
