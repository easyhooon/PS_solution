# 문제의 특이 조건
# 단, 같은 수를 두 번 이상 연속해서 사용하면 안 된다.
import sys
n = int(sys.stdin.readline().rstrip())

# dp[i][j] -> 첫번째로 오는 수가 j일때 1,2,3의 합으로 정수 i을 만드는 경우의 수 
# ex) 표현하고자 하는 숫자가 n일 경우
# 합의 첫번째 수로 1을 선택
# 1 + (n-1) -> n-1을 1,2,3의 합으로 표현할때는 합의 첫번째 수가 2 or 3 이어야 됨 -> 1+2+n-3 과 1+3+n-4 로 분할
# 
# 합의 첫번째 수로 2를 선택
# 2 + (n-2) -> n-2를 i-1개의 숫자로 표현할때는 합의 첫번째 수가 1 or 3 이어야 됨 -> 2+1+n-3 과 1+3+n-4 로 분할
# 
# 합의 첫번째 수로 3을 선택
# 3 + (n-3) -> n-3을 i-1개의 숫자로 표현할때는 합의 첫번째 수가 1 or 2 이어야됨 -> 3+1+n-4 과 3+2+n-5 로 분할

dp = [[0 for i in range(4)] for j in range(100001)]

#기저조건
dp[1][1] = 1  # 1 = 1
dp[2][2] = 1  # 2 = 2
dp[3][1] = 1  # 3 = 1 + 2
dp[3][2] = 1  # 3 = 2 + 1
dp[3][3] = 1  # 3 = 3

for i in range(4, 100001):
    dp[i][1] = (dp[i - 1][2] + dp[i - 1][3])%1000000009
    dp[i][2] = (dp[i - 2][1] + dp[i - 2][3])%1000000009
    dp[i][3] = (dp[i - 3][1] + dp[i - 3][2])%1000000009

for i in range(0, n):
    k = int(sys.stdin.readline().rstrip())

    ans = dp[k][1] + dp[k][2] + dp[k][3]
    print(ans % 1000000009) 