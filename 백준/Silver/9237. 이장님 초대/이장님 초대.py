import sys

# 가장 자라는데 오래걸리는 나무를 빨리 심자

def si():
    return sys.stdin.readline().rstrip()


n = int(si())
arr = list(map(int, si().split()))
arr.sort(reverse=True)
max_val = arr[0]
tmp = [0 for _ in range(n)]

for i in range(n):
    tmp[i] = arr[0] - i

# print(arr)
# print(tmp)

diff = [x - y for x, y in zip(arr, tmp)]
# print(diff)
print(arr[0] + max(diff) + 2)
