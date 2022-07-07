import sys

from itertools import permutations

si = sys.stdin.readline
n = int(si())
l = []

for i in range(1,n+1):
    l.append(i)

result = list(permutations(l, n))

# enumerate 는 튜플로 반환
# for element in enumerate(result):
#     print(element[1])

for i in result:
    for j in i:
        print(j, end=' ')
    print()

