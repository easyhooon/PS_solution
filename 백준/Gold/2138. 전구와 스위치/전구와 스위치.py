N = int(input())
initial = list(input().strip())
target = list(input().strip())

# 스위치를 눌러 전구 상태 변경
def flip(lst, idx):
    if idx > 0: 
        lst[idx-1] = '0' if lst[idx-1] == '1' else '1'
    lst[idx] = '0' if lst[idx] == '1' else '1'
    if idx < N-1: 
        lst[idx+1] = '0' if lst[idx+1] == '1' else '1'

# 현재 상태에서 스위치를 최소 몇 번 눌러야하는지 계산
def count_flips(current, target):
    count = 0
    for i in range(1, N):
        if current[i-1] != target[i-1]:
            flip(current, i)
            count += 1
    if current == target:
        return count
    return float('inf')

# 첫 번째 전구를 누르는 경우와 누르지 않는 경우
no_flip = initial[:]
flip_first = initial[:]
flip(flip_first, 0)

res1 = count_flips(no_flip, target)
res2 = count_flips(flip_first, target) + 1

if res1 == float('inf') and res2 == float('inf'):
    print(-1)
else:
    print(min(res1, res2))
