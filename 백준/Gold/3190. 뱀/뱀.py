import sys

# x, y 방향 지정
# 맵 안에 1 -> 사과, 0 -> 빈칸, -1 -> 뱀

# 방향 시계방향 (오른쪽으로 90도씩 회전 배열)
# 행과 열을 명시하였으므로 map[y][x]의 형태가 안헷갈릴것같다..
# 초기 조건에 오른쪽을 향한다라고 되어있으므로
dy = [0, 1, 0, -1]
dx = [1, 0, -1, 0]


def si():
    return sys.stdin.readline().rstrip()


n = int(si())
k = int(si())

grid = [[0 for _ in range(101)] for _ in range(101)]
cmd = [0] * 10001
snake_y = [0] * 10101
snake_x = [0] * 10101

for _ in range(k):
    y, x = map(int, si().split())
    grid[y][x] = 1

l = int(si())
for _ in range(l):
    time, c = map(str, si().split())
    cmd[int(time)] = c

time = 0
# 방향
dir = 0
# 처음 위치로 초기화
head_y, head_x, tail_index = 1, 1, time
snake_y[time], snake_x[time], = head_y, head_x,
# 현재 해당 칸에 뱀이 들어가 있는 경우 -1
grid[head_y][head_x] = -1

while True:
    time += 1

    head_y += dy[dir]
    head_x += dx[dir]

    # 맵을 벗어나거나 #뱀의 머리가 뱀의 몸통에 닿거나
    if head_y < 1 or head_y > n or head_x < 1 or head_x > n or grid[head_y][head_x] == -1:
        break

    snake_y[time] = head_y
    snake_x[time] = head_x

    # 빈칸 (그냥 진행)
    if grid[head_y][head_x] == 0:
        tail_y = snake_y[tail_index]
        tail_x = snake_x[tail_index]
        # 꼬리좌표 지우기 (빈칸으로)
        grid[tail_y][tail_x] = 0
        tail_index += 1

    # 뱀의 머리좌표 진행
    grid[head_y][head_x] = -1

    # 왼쪽으로 90도 방향 회전
    # 시계방향으로 증가하는 순이므로 3칸 증가 -> 왼쪽(90도 세번)
    # (dir -1) 로도 표현할 수 있으나 음수가 될 수 있음
    if cmd[time] == 'L':
        dir = (dir + 3) % 4


    # 오른쪽으로 90도 방향 회전
    # 시계방향으로 증가하는 순이므로 1칸 증가 -> 오른쪽
    if cmd[time] == 'D':
        dir = (dir + 1) % 4

print(time)
