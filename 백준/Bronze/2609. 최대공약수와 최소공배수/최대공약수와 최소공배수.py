import sys
import math

si = sys.stdin.readline()

N, M = map(int, si.split())

print(math.gcd(N, M))
print(math.lcm(N, M))
