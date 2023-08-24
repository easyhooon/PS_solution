# 전구와 스위치
import sys

si = sys.stdin.readline

n = int(si())
initial = list(si().strip())
target = list(si().strip())


def flip(lst, idx):
    if idx > 0:
        lst[idx - 1] = '0' if lst[idx - 1] == '1' else '1'
    lst[idx] = '0' if lst[idx] == '1' else '1'
    if idx < n - 1:
        lst[idx + 1] = '0' if lst[idx + 1] == '1' else '1'


def count_flips(current, target):
    count = 0
    for i in range(1, n):
        if current[i - 1] != target[i - 1]:
            flip(current, i)
            count += 1
    if current == target:
        return count
    return float('inf')


# 첫 번째 전구를 누르는 경우와 누르지 않는 경우
no_flip = initial[:]
flip_first = initial[:]
flip(flip_first, 0)

result1 = count_flips(no_flip, target)
result2 = count_flips(flip_first, target) + 1

if result1 == float('inf') and result2 == float('inf'):
    print(-1)
else:
    print(min(result1, result2))
