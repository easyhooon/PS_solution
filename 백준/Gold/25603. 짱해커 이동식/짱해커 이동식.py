import sys


def si():
    return sys.stdin.readline().rstrip()


# 주석은 시간초과 코드 

# k번 이하로 거절 햇을때 가장 높은 비용으로 target이 가능한지
# 이거 백트래킹으로 구해야되는건가? 반복문으론 못구하나 모든 경우의 수를
# n 개 중에 몇개를 뽑는 문제가 아니라
# 순차적으로 순회 했을때 k번 이하로 걸러서 뽑는 경우의 수를 구하는 건데
# => k 개씩 묶었을 때 min 값의 최댓값으로 판단하면 됨 (슬라이딩 윈도우 스타일)
def is_possible(target):
    # global k
    # max_value = -sys.maxsize
    # for i in range(n - k + 1):
    #     # cur_min = arr[i]
    #     # for j in range(i + 1, i + k):
    #     #     cur_min = min(cur_min, arr[j])
    #     part_arr = arr[i:i + k]
    #     cur_min = min(part_arr)
    #     max_value = max(max_value, cur_min)
    #
    # return max_value <= target

    # 대박이다 어떻게 생각하냐 이런걸
    # 굳이 k개씩 묶어서 탐색하지 않아도 된다. 선형으로 검사하면서 조건 오버를 카운팅하면된다. 그게 끊기면 다시 초기화 하는 형식으로 
    # 비용 tartget으로 의뢰를 연속으로 k-1번씩만 거절하면서 모든 의뢰를 해결할 수 있는가? 라는 가짜문제를 만들어서 선형 시간에 해결 
    cnt = 0
    for i in range(n):
        if arr[i] > target:
            cnt += 1
            if cnt >= k:
                return False
        else:
            cnt = 0

    return True


n, k = map(int, si().split())
arr = list(map(int, si().split()))

L, R = 1, 1000000000

# 1 3 5 4 2
# k = 2
# 2번 이상 거절하면 안될때 최댓값의 최소
# 1 3 5
# 2 4
# 따라서 4

# k가 2이면 가장 큰 수 하나를 거를 수 있음
# k가 3이면 가장 큰 수를 연속적으로 2개씩 거를 수 있음 음 아닌거 같아
# 1 999 100 1 1 343 279 1 이어도 1만 고를 수 있잖아... 정렬 하는 문제가 아님

# 1 100 100 1 1 100 100 1
# 1 1 1 1 <- 1 가능

ans = sys.maxsize

while L <= R:
    mid = (L + R) // 2
    if is_possible(mid):
        ans = mid
        R = mid - 1
    else:
        L = mid + 1

print(ans)
