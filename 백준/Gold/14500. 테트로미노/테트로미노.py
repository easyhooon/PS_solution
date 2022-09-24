import sys

si = sys.stdin.readline

# 최댓값에서의 커팅 방법

def in_range(x, y):
    return 0 <= x < n and 0 <= y < m


def can_go(x, y):
    return in_range(x, y) and not visited[x][y]


def get_max_sum(x, y, idx, sum):
    global max_sum

    if max_sum >= sum + max_val * (3 - idx):
        return

    if idx == 3:
        max_sum = max(max_sum, sum)
        return

    else:
        for dx, dy in zip(dxs, dys):
            nx = x + dx
            ny = y + dy
            if can_go(nx, ny):
                if idx == 1:
                    visited[nx][ny] = True
                    get_max_sum(x, y, idx + 1, sum + grid[nx][ny])
                    visited[nx][ny] = False
                visited[nx][ny] = True
                get_max_sum(nx, ny, idx + 1, sum + grid[nx][ny])
                visited[nx][ny] = False


n, m = map(int, si().split())
grid = [list(map(int, si().split())) for _ in range(n)]
visited = [[False for _ in range(m)] for _ in range(n)]

dxs, dys = [-1, 0, 1, 0], [0, 1, 0, -1]
max_sum = 0
max_val = max(map(max, grid))

for x in range(n):
    for y in range(m):
        visited[x][y] = True
        get_max_sum(x, y, 0, grid[x][y])
        visited[x][y] = False

print(max_sum)
