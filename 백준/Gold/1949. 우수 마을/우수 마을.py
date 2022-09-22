import sys
sys.setrecursionlimit(10005)


# 이해가 안감

def si():
    return sys.stdin.readline().rstrip()


def dfs(x, parent):
    dp[x][1] = num[x]
    for y in con[x]:
        if y == parent:
            continue
        dfs(y, x)
        dp[x][0] += max(dp[y])
        dp[x][1] += dp[y][0]


n = int(si())
num = [0] + list(map(int, si().split()))
# 빈 리스트로 만들 수 있구나
con = [[] for _ in range(n + 1)]
dp = [[0, 0] for _ in range(n + 1)]

for _ in range(n - 1):
    x, y = map(int, si().split())
    con[x].append(y)
    con[y].append(x)

# print(con)

dfs(1, -1)

print(max(dp[1][0], dp[1][1]))
