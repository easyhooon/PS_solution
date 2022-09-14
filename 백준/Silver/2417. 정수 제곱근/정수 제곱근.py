import sys


def si():
    return sys.stdin.readline().rstrip()


def is_possible(mid):
    return mid**2 >= n

n = int(si())

L, R = 0, n
ans = sys.maxsize

while L <= R:
    mid = (L + R) // 2
    if is_possible(mid):
        ans = mid
        R = mid - 1
    else:
        L = mid + 1

print(ans)