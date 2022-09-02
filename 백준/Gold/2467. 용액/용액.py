# 용액
# 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값을 출력, 출력해야하는 두 용액의 특성값은 오름차순으로 출력,
# 특성값이 0에 가장 가까운 용액을 만들어내는 경우가 두 개 이상인 경우 그 중 아무것이나 하나를 출력
# 특성값 = 두용액의 합!, 왜 마음대로 뺌;; -> 특성값의 합이 0에 가까울려면 빼는게
import sys


def si():
    return sys.stdin.readline().rstrip()


n = int(si())
a = list(map(int, si().split()))

# 투포인터도 정렬...
a.sort()

# 양쪽에서 다가오는 풀이가 적절할듯

L, R = 0, n - 1
ans = abs(a[R] + a[L])
ans_L, ans_R = 0, n - 1

while L + 1 != R:
    if abs(a[L]) < abs(a[R]):
        R -= 1
    else:
        L += 1

    if ans > abs(a[R] + a[L]):
        ans = abs(a[R] + a[L])
        ans_L, ans_R = L, R

print(a[ans_L], a[ans_R])
