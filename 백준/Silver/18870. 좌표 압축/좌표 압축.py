import sys


def si():
    return sys.stdin.readline().rstrip()

n = int(si())
a = list(map(int, si().split()))
nums = sorted(set(a))

mapper = dict()
cnt = 0
for num in nums:
    mapper[num] = cnt
    cnt += 1

#print(mapper)

for elem in a:
    print(mapper[elem], end=" ")

