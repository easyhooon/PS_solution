import math

# 방문 배열을 통해 중복 계산 제거

m, n = map(int, input().split())
isPrime = [True for _ in range(n + 1)]

sqrtOfN = int(math.sqrt(n))

# 2의 배수, 3의 배수, 5의 배수 ... sqrt 의 배수를 걸러낸다.
for i in range(2, sqrtOfN + 1):  # (1)
    # 이미 지워진 경우
    if not isPrime[i]:
        continue
    # 소수의 배수 제외
    else:
        j = 2
        while i * j <= n:
            if isPrime[i * j]:
                isPrime[i * j] = False
            j += 1

for i in range(m, n + 1):
    if i == 1:
        continue
    if isPrime[i]:
        print(i)
