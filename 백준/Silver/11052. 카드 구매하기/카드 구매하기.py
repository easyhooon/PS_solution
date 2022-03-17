import sys
n = int(sys.stdin.readline().rstrip())
dp = [0 for _ in range(0,n+1)]
k = list(map(int, input().split()))
k.insert(0, 0)


for i in range(1, n+1):
    for j in range(1, i+1):
        dp[i] = max(dp[i], dp[i-j] + k[j])

print(dp[n])