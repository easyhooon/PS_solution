# 일반적으로 for문으로 푸는 것이 재귀보다 성능이 좋기 때문에 dfs로 풀어도 좋지만 for문으로 푸는 연습도 해야한다.

import sys


def si():
    return sys.stdin.readline().rstrip()


# 모든 테트로미노의 경우의수를 모든 칸에 넣어서 확인해야하나
# ㅇㅇ 각 테트로미노 마다 경우의 수 총 19가지를 미리 만들어놔야 -> 그대로 놓고 한 칸 씩 옮겨 가면서 비교
shapes = [
    # 1
    [[1, 1, 1, 1],
     [0, 0, 0, 0],
     [0, 0, 0, 0],
     [0, 0, 0, 0]],

    [[1, 0, 0, 0],
     [1, 0, 0, 0],
     [1, 0, 0, 0],
     [1, 0, 0, 0]],

    # 정사각형
    [[1, 1, 0, 0],
     [1, 1, 0, 0],
     [0, 0, 0, 0],
     [0, 0, 0, 0]],

    # ㄱ 자
    [[1, 0, 0, 0],
     [1, 0, 0, 0],
     [1, 1, 0, 0],
     [0, 0, 0, 0]],

    [[0, 1, 0, 0],
     [0, 1, 0, 0],
     [1, 1, 0, 0],
     [0, 0, 0, 0]],

    [[1, 1, 1, 0],
     [1, 0, 0, 0],
     [0, 0, 0, 0],
     [0, 0, 0, 0]],

    [[1, 0, 0, 0],
     [1, 1, 1, 0],
     [0, 0, 0, 0],
     [0, 0, 0, 0]],

    [[1, 1, 1, 0],
     [0, 0, 1, 0],
     [0, 0, 0, 0],
     [0, 0, 0, 0]],

    [[0, 0, 1, 0],
     [1, 1, 1, 0],
     [0, 0, 0, 0],
     [0, 0, 0, 0]],

    [[1, 1, 0, 0],
     [0, 1, 0, 0],
     [0, 1, 0, 0],
     [0, 0, 0, 0]],

    [[1, 1, 0, 0],
     [1, 0, 0, 0],
     [1, 0, 0, 0],
     [0, 0, 0, 0]],

    # 번개
    [[1, 0, 0, 0],
     [1, 1, 0, 0],
     [0, 1, 0, 0],
     [0, 0, 0, 0]],

    [[0, 1, 0, 0],
     [1, 1, 0, 0],
     [1, 0, 0, 0],
     [0, 0, 0, 0]],

    [[1, 1, 0, 0],
     [0, 1, 1, 0],
     [0, 0, 0, 0],
     [0, 0, 0, 0]],

    [[0, 1, 1, 0],
     [1, 1, 0, 0],
     [0, 0, 0, 0],
     [0, 0, 0, 0]],

    # ㅗ
    [[1, 1, 1, 0],
     [0, 1, 0, 0],
     [0, 0, 0, 0],
     [0, 0, 0, 0]],

    [[0, 1, 0, 0],
     [1, 1, 1, 0],
     [0, 0, 0, 0],
     [0, 0, 0, 0]],

    [[1, 0, 0, 0],
     [1, 1, 0, 0],
     [1, 0, 0, 0],
     [0, 0, 0, 0]],

    [[0, 1, 0, 0],
     [1, 1, 0, 0],
     [0, 1, 0, 0],
     [0, 0, 0, 0]],
]

# def get_max_sum(x, y):
#     is_possible = True
#     max_sum = 0
#     for i in range(19):
#         sum_of_nums = 0
#         for dx in range(4):
#             for dy in range(4):
#                 if shapes[i][dx][dy] == 0:
#                     continue
#                 if x + dx >= n or y + dy >= m:
#                     is_possible = False
#                     break
#                 else:
#                     sum_of_nums += grid[x + dx][y + dy]
#             if not is_possible:
#                 break
#         if is_possible:
#             max_sum = max(max_sum, sum_of_nums)
#     return max_sum

def get_max_sum(x, y):
    max_sum = 0
    for i in range(19):
        sum_of_nums = get_sum_of_nums(i, x, y)
        max_sum = max(max_sum, sum_of_nums)
    return max_sum


def get_sum_of_nums(i, x, y):
    sum_of_nums = 0
    for dx in range(4):
        for dy in range(4):
            if shapes[i][dx][dy] == 0:
                continue
            if x + dx >= n or y + dy >= m:
                return 0
            else:
                sum_of_nums += grid[x + dx][y + dy]

    return sum_of_nums


n, m = map(int, si().split())
grid = [list(map(int, si().split())) for _ in range(n)]

ans = 0

for i in range(n):
    for j in range(m):
        ans = max(ans, get_max_sum(i, j))

print(ans)
