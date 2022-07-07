import sys

def factorial(n):
    if n == 0:
        return 1
    return n * factorial(n - 1)


si = sys.stdin.readline
n, k = map(int, si().split())

print(factorial(n) // (factorial(k) * factorial(n - k)))