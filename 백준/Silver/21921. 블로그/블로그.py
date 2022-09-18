import sys


def si():
    return sys.stdin.readline().rstrip()


cur_sum = 0
max_sum = 0
max_cnt = 1

n, k = map(int, si().split())
arr = list(map(int, si().split()))

for i in range(n - k + 1):
    if i == 0:
        for j in range(k):
            cur_sum += arr[j]
        max_sum = cur_sum

    else:
        # 전체적으로 한칸씩 오른쪽으로 이동한다고 생각해보면
        # 한칸 이동한 맨 왼쪽 값은 빼고
        # 한칸 이동한 맨 오른쪽 값은 더해준다.
        cur_sum -= arr[i - 1]
        cur_sum += arr[i + k - 1]
        if cur_sum == max_sum:
            max_cnt += 1
        elif cur_sum > max_sum:
            max_sum = cur_sum
            max_cnt = 1

if max_sum == 0:
    print("SAD")
else:
    print(max_sum)
    print(max_cnt)
