import sys

si = sys.stdin.readline

n, m = map(int, si().split())
answer = []


def choose(curr_num):
    if len(answer) == m:
        for elem in answer:
            print(elem, end=" ")
        print()
        return

    if curr_num > n:
        return

    answer.append(curr_num)
    choose(curr_num + 1)
    answer.pop()

    choose(curr_num + 1)

choose(1)