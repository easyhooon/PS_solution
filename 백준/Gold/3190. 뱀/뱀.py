import sys

# 1 -> 사과,
# 0 -> 빈칸,
# -1 -> 뱀

# 초기 조건에서 오른쪽으로 향하고 있음
si = sys.stdin.readline

dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]

# 보드의 크기 <= 100
n = int(si())
# 사과의 개수 <= 100
k = int(si())

board = [[0 for _ in range(101)] for _ in range(101)]
cmd = [0] * 10011

# 뱀이 이동한 경로를 시간 순서대로 저장
snake_x = [0] * 10011
snake_y = [0] * 10011

for _ in range(k):
    x, y = map(int, si().split())
    board[x][y] = 1

# 뱀의 방향 변환 횟수 <= 100
l = int(si())

for _ in range(l):
    time, c = map(str, si().split())
    cmd[int(time)] = c

# 시간
time = 0
# 방향
dir = 3

# 처음 위치 초기화
# tail_index 이 배열에서 뱀의 꼬리가 위치한 인덱스,
# 즉 뱀이 이동한 경로 중에서 가장 오래된 위치를 가리킴
head_x, head_y, tail_index = 1, 1, time
#
snake_x[time], snake_y[time] = head_x, head_y
# 현재 해당 칸에 뱀이 들어가 있는 경우 -> -1
board[head_x][head_y] = -1

while True:
    # 시간 진행
    time += 1

    # 머리 좌표 현재 방향에 맞춰 갱신
    head_x += dx[dir]
    head_y += dy[dir]

    # 맵을 벗어나거나, 뱀의 머리가 뱀의 몸통에 닿을 경우
    if (head_x < 1 or head_x > n or head_y < 1 or head_y > n) or board[head_x][head_y] == -1:
        break

    # 머리 위치 갱신
    snake_x[time] = head_x
    snake_y[time] = head_y

    # 진행
    if board[head_x][head_y] == 0:
        # 뱀의 현재 꼬리 위치 가져오기
        tail_x = snake_x[tail_index]
        tail_y = snake_y[tail_index]

        # 이전에 위치했던 꼬리 좌표 뱀 몸통에서 제거
        board[tail_x][tail_y] = 0
        # 꼬리 위치 갱신
        tail_index += 1

    # 뱀의 머리 좌표 진행
    board[head_x][head_y] = -1

    # 왼쪽으로 90 도 방향 회전
    if cmd[time] == 'L':
        dir = (dir + 1) % 4

    # 오른쪽으로 90도 방향 회전
    if cmd[time] == 'D':
        dir = (dir + 3) % 4

print(time)