# 할로윈의 양아치

# union-fin
# 최대로 뺏을 수 있는 사탕의 양(dp?)
import sys

sys.setrecursionlimit(10 ** 6)

si = sys.stdin.readline


def union(x, y):
    x = find(x)
    y = find(y)
    uf[x] = y


def find(x):
    if uf[x] == x:
        return x

    root_node = find(uf[x])
    uf[x] = root_node
    return root_node


#
# N 거리에 있는 아이들의 수,
# M 아이들의 친구 관계 수,
# K는 울음소리가 공명하기 위한 최소 아이의 수
n, m, k = map(int, si().split())
candy_arr = [0] + list(map(int, si().split()))

uf = [0] * (n + 1)

for i in range(1, n + 1):
    uf[i] = i

for _ in range(m):
    x, y = map(int, si().split())
    union(x, y)

# print(uf)

group_candy = [0] * (n + 1)
group_size = [0] * (n + 1)

for i in range(1, n + 1):
    root = find(i)
    group_candy[root] += candy_arr[i]
    group_size[root] += 1

arr = []
for i in range(1, n + 1):
    if group_size[i] > 0:
        arr.append((group_size[i], group_candy[i]))

dp = [0] * (k + 1)
for size, candy in arr:
    for j in range(k, -1, -1):
        if j - size >= 0:
            dp[j] = max(dp[j], dp[j - size] + candy)

print(dp[k - 1])
