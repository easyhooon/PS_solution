import sys
n = int(sys.stdin.readline().rstrip())
d = []
hs = []

for i in range(1, n+1):
     d.append(i)

if n < 100:
    for i in range(1, n + 1):
        hs.append(d[i - 1])
    n = len(hs)
    print(n)


else:

    for i in range(1, 100):
        hs.append(d[i - 1])

    for i in range(100, n+1):
        if (d[i-1]//100 - (d[i-1]//10 - d[i-1]//100*10)) == (d[i-1]//10 - d[i-1]//100*10) - (d[i-1] - d[i-1]//10*10):
            hs.append(d[i-1])
    n = len(hs)
    print(n)