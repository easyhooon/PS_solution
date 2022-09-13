import sys


def si():
    return sys.stdin.readline().rstrip()


def preprocess():
    dp[1] = 1
    dp[2] = 1
    dp[3] = 1
    dp[4] = 2
    dp[5] = 2
    
    for i in range(6, 101):
        dp[i] = dp[i-1] + dp[i-5]

    #print(dp)


tc = int(si())
dp = [0] * 101

preprocess()

for _ in range(tc):
    n = int(si())
    print(dp[n])


