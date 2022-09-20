import sys
from collections import deque
from pprint import pprint


def si():
    return sys.stdin.readline().rstrip()


def in_range(x, y):
    return 0 <= x < n and 0 <= y < n


def can_go(x, y):
    # 상어랑 같은 사이즈까진 이동할 수 있다.
    return in_range(x, y) and grid[x][y] <= shark_size and dist[x][y] == -1


def init_dist():
    for i in range(n):
        for j in range(n):
            dist[i][j] = -1


def bfs(fish_x, fish_y):
    global shark_x, shark_y
    init_dist()
    q = deque()
    q.append((shark_x, shark_y))
    dist[shark_x][shark_y] = 0

    while q:
        x, y = q.popleft()
        if x == fish_x and y == fish_y:
            # min_dist = dist[x][y]
            # return min_dist, x, y
            return

        for dx, dy in zip(dxs, dys):
            nx = x + dx
            ny = y + dy
            if can_go(nx, ny):
                dist[nx][ny] = dist[x][y] + 1
                q.append((nx, ny))


def shark_update(min_time, min_x, min_y):
    global total_time, shark_x, shark_y, shark_exp, shark_size
    total_time += min_time
    # shark_exp += grid[min_x][min_y]
    shark_exp += 1
    # 물고기 제거
    grid[min_x][min_y] = 0
    # 상어 위치 갱신
    shark_x = min_x
    shark_y = min_y

    if shark_exp >= shark_size:
        shark_size += 1
        shark_exp = 0


# 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다
# 상어는 자신의 크기와 같은 수의 물고기를 먹을 때마다 크기가 1 증가한다.
# 상어랑 같은 크기의 물고기가 존재하는 칸은 이동할 수 있다.
def shark_move():
    global shark_exp, shark_size, shark_x, shark_y, total_time
    flag = False
    min_time = sys.maxsize
    min_x = sys.maxsize
    min_y = sys.maxsize

    # 상어 위치 계속 가지고 있음

    # 상어 이동
    for i in range(n):
        for j in range(n):
            # 먹을 수 있는 물고기 위치
            if 0 < grid[i][j] < shark_size:
                # flag = True
                # 먹을 수 있는 물고기가 존재는 해도 길이 자신보다 큰 물고기들로 막혀 해당 물고기를 못 먹는 경우의 수가 존재
                # (time, x, y) = bfs(i, j)
                bfs(i, j)
                # pprint(dist)
                # dist[i][j]에 도달 못해도 다른 물고기 위치엔 도달할 수 있다. 하나만 보고 못가네->return False 처리하면 안된다.
                time = dist[i][j]
                x, y = i, j
                if dist[i][j] == -1:
                    # return False
                    continue
                else:
                    flag = True
                    # 같은 거리에 여러 마리의 먹을 수 있는 물고기가 있을 수 있음
                    if time < min_time:
                        min_time = time
                        min_x = x
                        min_y = y

                    elif time == min_time:
                        if x < min_x:
                            min_x = x
                            min_y = y

                        elif x == min_x:
                            if y < min_y:
                                min_x = x
                                min_y = y

    # 아기 상어가 물고기를 먹었으면
    if flag:
        # print(min_time, min_x, min_y, grid[min_x][min_y])
        shark_update(min_time, min_x, min_y)

    return flag


def simulation():
    global shark_x, shark_y
    flag = True
    # pprint(grid)

    while flag:
        flag = shark_move()
        # print()
        # pprint(grid)
        # print(total_time, shark_x, shark_y, shark_exp, shark_size)

    print(total_time)


n = int(si())
grid = [list(map(int, si().split())) for _ in range(n)]
dist = [[-1 for _ in range(n)] for _ in range(n)]
dxs, dys = [-1, 1, 0, 0], [0, 0, -1, 1]
shark_x, shark_y = 0, 0
shark_size = 2
shark_exp = 0
total_time = 0

# 상어 위치 찾기
for i in range(n):
    for j in range(n):
        if grid[i][j] == 9:
            shark_x = i
            shark_y = j
            grid[i][j] = 0

simulation()
