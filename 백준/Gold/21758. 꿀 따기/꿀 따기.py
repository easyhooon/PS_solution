# 꿀따기
# 그리디, 누적합
import sys

si = sys.stdin.readline

n = int(si())
arr = [0] + list(map(int, si().split()))
max_honey_sum = -sys.maxsize
# 왼쪽부터 누적합
# 오른쪽부터 누적합 차례로 구해놓자
# 꿀통 i j 일때는 꿀통, j 가 양끝일때가 최대
# i 꿀통 j 일때는 i, j 가 양끝일때가 최대
# i j 꿀통일때는 i, 꿀통이 양끝일때가 최대
# 왼쪽 부터 누적합
prefix_arr_left = [0] * (n + 2)
# 오른쪽부터 누적합
# prefix_arr_right = [0] * (n + 2)

# 벌의 위치는 서로 다름, 벌통도
# 꿀은 무조건 오른쪽 끝에 있는게 합을 크게 만들 수 있다.(꿀 위치 고정)
# 아 아님 꿀이 가운데에 있고 두 벌이 가운데로 향하는 방법도 있음(그래서 예제 3번이 답이 10)
# 일단 완탐으로 먼저 풀고 그다음에 최적화를 고민
# 누적합은 방향에 따라 그 합이 달라진다

# def get_honey_sum(i, j, k):
#     honey_sum = 0
#     # 꿀통 i j
#     if k < i < j:
#         for i in range(2, n):
#
#         honey_sum += prefix_arr_left[j - 1] - prefix_arr_left[k - 1] - arr[i]
#         honey_sum += prefix_arr_left[i - 1] - prefix_arr_left[k - 1]
#
#     # i 꿀통 j
#     elif i < k < j:
#         honey_sum += prefix_arr_left[j - 1] - prefix_arr_left[k - 1]
#         honey_sum += prefix_arr_left[k] - prefix_arr_left[i]
#
#     # i j 꿀통
#     elif i < j < k:
#         honey_sum += prefix_arr_left[k] - prefix_arr_left[j]
#         honey_sum += prefix_arr_left[k] - prefix_arr_left[i] - arr[j]
#
#     return honey_sum


for i in range(1, n + 1):
    prefix_arr_left[i] = prefix_arr_left[i - 1] + arr[i]

# for i in range(n, 0, - 1):
#     prefix_arr_right[i] = prefix_arr_right[i + 1] + arr[i]

# print(prefix_arr_left)
# print(prefix_arr_right)

# for i in range(n - 1):
#     for j in range(i + 1, n):
#         for k in range(n):
#             if i == k or j == k:
#                 continue

# print(i, j, k, get_honey_sum(i, j, k))
# max_honey_sum = max(max_honey_sum, get_honey_sum(i, j, k))

# 벌1, 벌2, 꿀통
for i in range(2, n):
    max_honey_sum = max(max_honey_sum,
                        (prefix_arr_left[n] - prefix_arr_left[1] - arr[i]) + (prefix_arr_left[n] - prefix_arr_left[i]))

# 꿀통 벌1, 벌2
# for i in range(n - 1, 1, - 1):
#     max_honey_sum = max(max_honey_sum,
#                         (prefix_arr_right[1] - prefix_arr_right[n] - arr[i]) + (prefix_arr_right[1] - prefix_arr_right[i]))

for i in range(2, n):
    max_honey_sum = max(max_honey_sum, prefix_arr_left[i - 1] + prefix_arr_left[n - 1] - arr[i])

# 벌1 꿀통 벌2
for i in range(2, n):
    # max_honey_sum = max(max_honey_sum,
    #                     (prefix_arr_left[i] - arr[1]) + (prefix_arr_right[i] - arr[n]))
    max_honey_sum = max(max_honey_sum,
                        (prefix_arr_left[i] - arr[1]) + (prefix_arr_left[n - 1] - prefix_arr_left[i - 1]))


print(max_honey_sum)
