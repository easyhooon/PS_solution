# A, B 문자열은 최대 1000글자로 이루어져 있다
# 길이 뿐만 아니라 문자열도 출력해줘야한다.

# 1. 마지막으로 고려한 문자열 A의 위치,
# 2. 마지막으로 고려한 문자열 B의 위치,
# 3. 지금까지 고려된 A,B의 부분 문자열의 공통 부분 수열의 길이
# -> 마지막으로 고려된 A,B의 부분 문자열의 상태가 동일한 경우, 공통 부분 수열의 길이는 길수록 더 좋다.

# dp[i][j] -> 첫번째 문자열의 i번째 문자까지 고려하고, 두 번째 문자열의 j번째 문자까지 고려했을 때 가능한 공통 부분 수열의 최장 길이

import sys

si = sys.stdin.readline


def preprocess():
    dp[1][1] = 1 if A[1] == B[1] else 0

    for i in range(2, len_A + 1):
        if A[i] == B[1]:
            dp[i][1] = 1
        else:
            dp[i][1] = dp[i - 1][1]

    for j in range(2, len_B + 1):
        if A[1] == B[j]:
            dp[1][j] = 1
        else:
            dp[1][j] = dp[1][j - 1]


# 해당 문제의 경우 이런식으로 zero padding 을 해주는 것이 깔끔하게 풀리는 것 같다.
A = " " + si().strip()
B = " " + si().strip()

len_A, len_B = len(A) - 1, len(B) - 1

dp = [[0 for _ in range(len_B + 1)] for _ in range(len_A + 1)]

preprocess()

for i in range(2, len_A + 1):
    for j in range(2, len_B + 1):
        # 두 문자가 일치 하는 경우
        # -> 해당 문자를 최장 공통수열 에 포함
        # -> 대각선 방향에 있는 값에 1을 더해줌
        if A[i] == B[j]:
            dp[i][j] = dp[i - 1][j - 1] + 1
        # 다를 경우
        # 1. A 문자열의 i번 문자를 B 문자열과 공통으로 묶어 처리하지 않을 경우 dp[i-1][j]
        # 2. B 문자열의 j번 문자를 A 문자열과 공통으로 묶어 처리하지 않을 경우 dp[i][j-1]
        # -> 위, 왼쪽 중 작은 값에 1을 더함(새로운 문자를 더하여 늘어난 개수)

        else:
            dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

# print(dp)
print(dp[len_A][len_B])

answer = ""
i, j = len_A, len_B

while i > 0 and j > 0:
    # 현재 위치의 문자가 일치하는 경우
    if A[i] == B[j]:
        answer = A[i] + answer
        i -= 1
        j -= 1
    # 현재 위치의 문자가 일치하지 않는 경우
    # 이전 행 또는 이전 열로 이동합니다.
    # (이전 행 또는 열 중 LCS의 길이가 더 큰 쪽으로 이동)
    elif dp[i-1][j] > dp[i][j-1]:
        i -= 1
    else:
        j -= 1

print(answer)