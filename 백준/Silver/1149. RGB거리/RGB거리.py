import sys
from pprint import pprint

n = int(sys.stdin.readline().rstrip())

board = [[0 for i in range(3)] for j in range(n+1)]
dp = [[0 for i in range(3)] for j in range(n+1)]

for i in range(1, n+1):
    k = list(map(int, input().split()))
    for j in range(0, 3):
        board[i][j] = k[j]
# pprint(board)

#처음 선택에 따라 케이스를 3가지로 나눠야함
dp[n][0] = board[n][0]
dp[n][1] = board[n][1]
dp[n][2] = board[n][2]

for i in range(n-1, 0, -1):
    for j in range(0, 3):
        dp[i][j] = min(dp[i+1][(j+1)%3], dp[i+1][(j+2)%3]) + board[i][j]

# pprint(dp)
print(min(dp[1][0], dp[1][1], dp[1][2]))

