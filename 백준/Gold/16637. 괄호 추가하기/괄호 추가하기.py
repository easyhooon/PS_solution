import sys

si = sys.stdin.readline

INT_MIN = - sys.maxsize

max_result = INT_MIN


def calc(num, op):
    while op:
        oper = op.pop(0)
        n1, n2 = num.pop(0), num.pop(0)
        if oper == '+':
            num.insert(0, n1 + n2)
        elif oper == '-':
            num.insert(0, n1 - n2)
        elif oper == '*':
            num.insert(0, n1 * n2)
    return num[0]


# 백트래킹
def choose(cnt, num, op):
    global max_result
    if cnt == n // 2 or len(num) == 1:
        max_result = max(max_result, calc(num, op))
        return

    choose(cnt + 1, num[:], op[:])

    # if len(num) > cnt + 2:
    #     n1, n2 = num.pop(cnt), num.pop(cnt)
    #     oper = op.pop(cnt)
    #
    #     if oper == '+':
    #         num.insert(cnt, n1 + n2)
    #     elif oper == '-':
    #         num.insert(cnt, n1 - n2)
    #     elif oper == '*':
    #         num.insert(cnt, n1 * n2)
    #
    #     choose(cnt + 1, num, op)

    try:
        n1, n2 = num.pop(cnt), num.pop(cnt)
        oper = op.pop(cnt)

        if oper == '+':
            num.insert(cnt, n1 + n2)
        elif oper == '-':
            num.insert(cnt, n1 - n2)
        elif oper == '*':
            num.insert(cnt, n1 * n2)

        choose(cnt + 1, num[:], op[:])

    except:
        0


n = int(si())
exp = si()
# 숫자와 연산자를 분리하여 담기
num, op = [], []
for i in range(n):
    if i % 2 == 0:
        num.append(int(exp[i]))
    else:
        op.append(exp[i])

choose(0, num, op)

print(max_result)
