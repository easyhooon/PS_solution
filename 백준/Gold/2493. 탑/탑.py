import sys


def si():
    return sys.stdin.readline().rstrip()


n = int(si())
height = [0] + list(map(int, si().split()))

stack = []

# 스택을 이용하여 풀이

# 목표: 총 비교 횟수를 줄이자.
# 탑 마다 자신의 왼쪽에 있는 모든 탑을 볼 필요가 있나?
# => 비교할 가치가 있는 탑만 비교하자
# 비교할 가치가 있는 배열을 만듬,
# 1번 탑 부터 차례대로 보며 비교할 가치가 있는 배열을 채움

stack.append(1)
print(0, end=" ")

for i in range(2, n + 1):
    while stack:
        if height[stack[-1]] < height[i]:
            stack.pop()
        else:
            print(stack[-1], end=" ")
            break
    if not stack:
        print(0, end=" ")
    stack.append(i)


