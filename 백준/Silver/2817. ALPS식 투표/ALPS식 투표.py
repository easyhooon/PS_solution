import sys

pnum = sys.stdin.readline().rstrip()
snum = sys.stdin.readline().rstrip()

pnum = int(pnum)
snum = int(snum)

info = []
score = []

ticket = [0 for _ in range(snum)]

for i in range(0, snum):
    a, b = map(str, input().split())
    info.append([a, b])

del_info = []
for i in range(0, len(info)):
    if int(info[i][1]) < (pnum * 0.05):
        # del(info[i])
        del_info.append(info[i])
        #여기서 인덱스를 제거했는데 for문에선 다음 인덱스로 넘어감 따라서 뒤의 인덱스가 앞으로 끌려오고 런타임 에러
        # snum -= 1

for i in del_info:
    tmp = i
    info.remove(tmp)
    snum -= 1

for i in range(0, len(info)):
    tmp = []
    for j in range(1, 15):
        tmp.append(int(info[i][1]) // j)
    score.append(tmp)

count = 0

while count != 14:
    p = 0
    for i in range(0, snum):
        # len(snum)하면 1이잖아;
        # 위에서 snum이 지워지면 -- 해줘야함! 런타임에러의 원인!

        if p < score[i][0]:
            p = score[i][0]
            index = i

    ticket[index] = ticket[index] + 1
    del(score[index][0])
    count += 1

result = []

for i in range(0, snum):
    temp = []
    temp.append(info[i][0])
    temp.append(ticket[i])

    result.append(temp)

result.sort()

for i in range(0, snum):
    print(result[i][0], end=' ')
    print(result[i][1])