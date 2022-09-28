import sys


# 로직 수정 필요
# 외부와 내부가 다시보니 독립적이다 분리해서 돌려야 한다.

# x == 0 or n
# y == 0 or m-1
# 다음
# x == 1 or n-1
# y == 1 or m-1
# 이런식으로 can_go의 내부 범위값을 수정하면서 해야겠다
# 그러면 한 사이클 완료했다는 거는 어떻게 판단할건데
# 음 굳이 grid을 하나만 사용해야할 이유가 있나
# 원래 판과 복사된 판 두개로 사용해서 복사돤 판이 모두 회전하면 그것을 원래의 판으로 교체하는 느낌으로 하면되잖아 -> 이게 훨씬 쉽지

def si():
    return sys.stdin.readline().rstrip()


def determine_direction():
    global x, y, x_start, x_end, y_start, y_end
    # print(x, y, x_start, x_end, y_start, y_end)
    if y == y_start:
        if x != x_end:
            if not visited[x + 1][y]:
                nx = x + 1
                ny = y
                copy_grid[nx][ny] = grid[x][y]
                visited[nx][ny] = True
                x = nx
                y = ny
                return True
            else:
                return False

    if x == x_end:
        if y != y_end:
            if not visited[x][y + 1]:
                nx = x
                ny = y + 1
                copy_grid[nx][ny] = grid[x][y]
                visited[nx][ny] = True
                x = nx
                y = ny
                return True
            else:
                return False

    if y == y_end:
        if x != x_start:
            if not visited[x - 1][y]:
                nx = x - 1
                ny = y
                copy_grid[nx][ny] = grid[x][y]
                visited[nx][ny] = True
                x = nx
                y = ny
                return True
            else:
                return False

    if x == x_start:
        if y != y_start:
            if not visited[x][y - 1]:
                nx = x
                ny = y - 1
                copy_grid[nx][ny] = grid[x][y]
                visited[nx][ny] = True
                x = nx
                y = ny
                return True
            else:
                return False


def init():
    global x, y, x_start, x_end, y_start, y_end
    change_grid()
    init_visited()

    x_start = 1
    x_end = n
    y_start = 1
    y_end = m
    x = 1
    y = 1


def init_visited():
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            visited[i][j] = False


def change_grid():
    global copy_grid
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            grid[i][j] = copy_grid[i][j]

    copy_grid = [[0] * (m + 1) for _ in range(n + 1)]


def simulate():
    global x, y, x_start, x_end, y_start, y_end
    cnt = 1

    while cnt <= n * m:
        if not determine_direction():
            # 한 사이클을 모두 돌고 더 이상 방문할 점이 없을 경우
            # print("사이클 끝")
            x += 1
            y += 1
            x_start += 1
            x_end -= 1
            y_start += 1
            y_end -= 1
        else:
            cnt += 1


n, m, r = map(int, si().split())
grid = [[0] * (m + 1)]
for _ in range(n):
    grid.append([0] + list(map(int, si().split())))
copy_grid = [[0] * (m + 1) for _ in range(n + 1)]


# 이동 방향의 우선 순위를 매기는 방식 으로는 풀 수 없을 것 같다.

# dx, dy = [0, 1, 0, -1], [-1, 0, 1, 0]
visited = [[False for _ in range(m + 1)] for _ in range(n + 1)]
x_start = 1
x_end = n
y_start = 1
y_end = m
x = 1
y = 1

while r > 0:
    simulate()
    # for i in range(1, n + 1):
    #     for j in range(1, m + 1):
    #         print(copy_grid[i][j], end=" ")
    #     print()
    # print()

    init()

    r -= 1

# for i in range(n+1):
#     print(*grid[i])

for i in range(1, n + 1):
    for j in range(1, m + 1):
        print(grid[i][j], end=" ")
    print()

# for i in range(1, n + 1):
#     for j in range(1, m + 1):
#         print(visited[i][j], end=" ")
#     print()
