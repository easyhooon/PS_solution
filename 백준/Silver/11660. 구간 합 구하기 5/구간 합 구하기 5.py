import sys

si = sys.stdin.readline

n, m = map(int, si().split())
queries = []

board = [list(map(int, si().split())) for _ in range(n)]
for _ in range(m):
    query = list(map(int, si().split()))
    queries.append(query)

prefix_sum = [[0] * (n + 1) for _ in range(n + 1)]
# print(prefix_sum)

for i in range(1, n + 1):
    for j in range(1, n + 1):
        prefix_sum[i][j] = board[i - 1][j - 1] + prefix_sum[i - 1][j] + prefix_sum[i][j - 1] - prefix_sum[i - 1][j - 1]
# print(prefix_sum)

for x1, y1, x2, y2 in queries:
    result = prefix_sum[x2][y2] - prefix_sum[x1 - 1][y2] - prefix_sum[x2][y1 - 1] + prefix_sum[x1 - 1][y1 - 1]
    print(result)