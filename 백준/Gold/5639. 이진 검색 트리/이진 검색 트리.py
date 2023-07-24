# 이진 검색 트리
import sys

sys.setrecursionlimit(10 ** 6)

si = sys.stdin.readline


def post_order(start, end):
    if start > end:
        return

    division = end + 1
    for i in range(start + 1, end + 1):
        if pre_order[start] < pre_order[i]:
            division = i
            break

    post_order(start + 1, division - 1)
    post_order(division, end)
    print(pre_order[start])


pre_order = []

for line in sys.stdin:
    pre_order.append(int(line))

# while True:
#     try:
#         pre_order.append(int(si()))
#     except:
#         break

post_order(0, len(pre_order) - 1)
