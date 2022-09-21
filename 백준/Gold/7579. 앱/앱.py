import sys

def si():
    return sys.stdin.readline().rstrip()


# dp[i][j] -> i번째 인덱스의 앱까지 고려했을때 바용 j 만큼 사용하여 얻을 수 있는 최대메모리
def preprocess():
    # Tabulation
    for i in range(1, n + 1):
        for j in range(10001):
            if cost[i] > j:
                dp[i][j] = dp[i - 1][j]
            else:
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - cost[i]] + memory[i])


def solution():
    for j in range(10001):
        if dp[n][j] >= m:
            print(j)
            return


n, m = map(int, si().split())
memory = [0] + list(map(int, si().split()))
cost = [0] + list(map(int, si().split()))

# dp[n][m] -> i번째 인덱스까지의 앱으로 메모리 j만큼 확보 할때 필요한 최소 가치(비용)
dp = [[0 for _ in range(10001)] for _ in range(n + 1)]

preprocess()
solution()