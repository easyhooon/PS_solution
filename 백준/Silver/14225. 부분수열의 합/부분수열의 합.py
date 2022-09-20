import sys


def si():
    return sys.stdin.readline().rstrip()


def solution():
    global sum_val
    for elem in arr:
        if sum_val + 1 < elem:
            print(sum_val + 1)
            return
        sum_val += elem
    print(sum_val + 1)


n = int(si())
arr = list(map(int, si().split()))
arr.sort()

sum_val = 0
solution()
