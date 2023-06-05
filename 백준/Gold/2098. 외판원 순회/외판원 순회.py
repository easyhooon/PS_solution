import sys

si= sys.stdin.readline

INT_MAX = sys.maxsize

n = int(si())
dist = [list(map(int, si().split())) for _ in range(n)]

# bitmask 이용시 편의상 정점 번호를 1부터 n까지가 아닌 0부터 n - 1 까지라 생각하고 진행 

# dp[i][j] : 
# 0번을 시작으로 겹치지 않게 방문한 위치를
# x1, x2, ..., xk라 헀을 때 
# 2^x1 + 2^x2 + ... + 2^xk 값이 i이고 (bitmask된 정수값이 i)
# 그래서 현재 서 있는 위치가 j가 되었을 때
# 가능한 최소 이동거리
dp = [[0] * n for _ in range(1 << n)]

# 최소 이동거리를 구하는 문제이기에
# 초기값으로 아주 큰 값을 넣어줍니다.
for i in range(1 << n):
    for j in range(n):
        dp[i][j] = INT_MAX

# 초기조건은
# 0번 지점을 시작으로 하며 현재 0번 위치에 서있으며
# 지금까지 이동한 거리가 0인 상태인
# dp[1][0] = 0이 됩니다.
dp[1][0] = 0

# 뿌려주는 방식의 dp를 진행합니다.
# dp[i][j]가 계산이 되어있다는 가정하에서
# 그 다음 상태값을 갱신합니다.
for i in range(1 << n):
    for j in range(n):
        # j번 지점을 방문한게 정의상 불가능 하다면
        # 패스합니다.
        if ((i >> j) & 1) == 0:
            continue

        # 현재 j번에서 그 다음 위치로 k번 지점을 가게 되는 경우
        # 상태값을 계산하여 최솟값을 갱신해줍니다.
        for k in range(n):
            # k번 지점을 이미 방문한 적이 있다면
            # 중복 방문은 조건상 불가하므로 패스합니다.
            if ((i >> k) & 1) == 1:
                continue
            
            # j번에서 k번으로 가는 길이 없다면
            # 패스합니다.
            if dist[j][k] == 0:
                continue
            
            dp[i + (1 << k)][k] = min(dp[i + (1 << k)][k], dp[i][j] + dist[j][k])

# 어디서 끝나던 상관이 없기 때문에
# 모든 지점을 방문한 경우 중 최솟값을 갱신합니다.
ans = INT_MAX
for i in range(1, n):
    # i번에서 1번으로 가는 길이 없다면 패스합니다.
    if dist[i][0] == 0:
        continue

    # 최솟값을 갱신합니다.
    ans = min(ans, dp[(1 << n) - 1][i] + dist[i][0])

print(ans)
