import sys


def si():
    return sys.stdin.readline().rstrip()


def rotate(layer):
    global grid

    for l in range(layer):
        tmp = grid[l][l]
        # 위
        for c in range(l, m - l - 1):
            grid[l][c] = grid[l][c + 1]

        # 우
        for r in range(l, n - 1 - l):
            grid[r][m - 1 - l] = grid[r + 1][m - 1 - l]

        # 아래
        for r in range(m - 1 - l, l, -1):
            grid[n - 1 - l][r] = grid[n - 1 - l][r - 1]

        # 좌
        for r in range(n - 1 - l, l, -1):
            grid[r][l] = grid[r - 1][l]

        grid[l + 1][l] = tmp


n, m, k = map(int, input().split())
grid = [list(map(int, si().split())) for _ in range(n)]

if n < m:
    layer = n // 2
    
else:
    layer = m // 2  # 몇 겹으로 되어 있는지

# rotate matrix
for _ in range(k):
    rotate(layer)

for row in grid:
    print(*row)
