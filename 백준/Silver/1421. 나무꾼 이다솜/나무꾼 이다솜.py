import sys


def si():
    return sys.stdin.readline().rstrip()


def solution(length):
    global max_value
    sum = 0
    for elem in trees:
        if elem >= length:
            if elem % length == 0:
                slice_cnt = elem // length - 1
                if elem * w - (slice_cnt * c) > 0:
                    sum += elem * w - (slice_cnt * c)
            else:
                slice_cnt = elem // length
                if (slice_cnt * length) * w - (slice_cnt * c) > 0:
                    sum += (slice_cnt * length) * w - (slice_cnt * c)

    max_value = max(max_value, sum)


n, c, w = map(int, si().split())
trees = [int(si()) for _ in range(n)]
trees.sort()

max_value = -sys.maxsize

for i in range(1, trees[-1] + 1):
    solution(i)

print(max_value)
