import sys
import heapq


# 최소 pq과 최대 pq, 즉 두개의 pq를 이용하여 중앙값을 빠르게 구할 수 있다.


def si():
    return sys.stdin.readline()


def find_mid():
    mid = arr[0]
    max_pq, min_pq = [], []
    print(mid)

    for i in range(1, n):

        # 짝수 (0번째 즉, 1개 원소를 미리 넣었으므로)
        if i % 2 == 1:
            # 중간에 있는 두 수 중에서 작은 수를 출력
            if arr[i] < mid:
                heapq.heappush(max_pq, -arr[i])
                temp = -heapq.heappop(max_pq)
                print(temp)
                heapq.heappush(max_pq, -temp)

            else:
                heapq.heappush(min_pq, arr[i])
                print(mid)

        # 홀수
        else:
            if len(max_pq) > len(min_pq):
                new_cand = -heapq.heappop(max_pq)
            else:
                new_cand = heapq.heappop(min_pq)

            nums = sorted([mid, arr[i], new_cand])

            heapq.heappush(max_pq, -nums[0])
            mid = nums[1]
            heapq.heappush(min_pq, nums[2])

            print(mid)
        # print(f'max_pq: {max_pq}')
        # print(f'min_pq: {min_pq}')


n = int(si())
arr = list([int(si()) for _ in range(n)])
# print(arr)

find_mid()
