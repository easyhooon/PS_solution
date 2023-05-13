import sys

si = sys.stdin.readline

n = int(si())

villages = []
total_population = 0

for _ in range(n):
    x, a = map(int, si().split())
    villages.append((x, a))
    total_population += a

villages.sort()

half_population = (total_population + 1) // 2

accumulated_population = 0
answer = 0

for x, a in villages:
    accumulated_population += a
    # 처음으로 중간에 도달한 마을의 위치 구하기
    if accumulated_population >= half_population:
        answer = x
        break

print(answer)

# total + 1 을 해주는 이유: 절반 이상의 인구수를 포함하는 마을을 찾기 위해서(홀수 일때 + 1 하여 절반 이상인 최초 정수 찾기, 짝수일 때는  + 1 해줘도 영향 x)
# 절반 "이상" 인 마을을 찾아야 하는 이유:
# 우체국이 설치될 위치를 고르는 문제에서 최소 거리를 보장하기 위함
# 우체국이 설치될 위치는 전체 인구의 절반 이상을 포함하는 마을이어야 그 위치에서 다른 마을까지의 거리 합이 최소가 됨
# 마을 A: 인구 2명
# 마을 B: 인구 6명
# 마을 C: 인구 2명
# 전체 인구는 10명이므로 절반 이상의 인구수는 5명.
# 이 경우, 우체국을 마을 B에 설치하는 것이 가장 합리적
# 왜냐하면 이렇게 하면 다른 마을(A, C)까지의 거리 합이 최소가 되기 때문
