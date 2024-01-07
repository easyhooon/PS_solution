import sys

si = sys.stdin.readline

# 1부터 n까지 자연수 중에서 중복없이 m 개를 고른 수열(사전순 출력)

# 3 1
# 1
# 2
# 3

n, m = map(int, si().split())
answer = []


def choose():
    if len(answer) == m:
        for elem in answer:
            print(elem, end=" ")
        print()
        return

    for i in range(1, n + 1):
        if i not in answer:
            answer.append(i)
            choose()
            answer.pop()


choose()