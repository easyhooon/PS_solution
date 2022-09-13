import sys

# n 으로 0 도 들어올 수 있다. 입력 범위 확인...

def si():
    return sys.stdin.readline().rstrip()


def preprocess():
    dp[0] = 1
    dp[1] = 1
    dp[2] = 3
    dp[3] = 5

    for i in range(4, 251):
        dp[i] = (dp[i - 1] + 2 * dp[i - 2])


dp = [0] * 251
preprocess()

while True:
    try:
        n = int(si())
        print(dp[n])
    # except EOFError:
    #     break
    except:
        break







