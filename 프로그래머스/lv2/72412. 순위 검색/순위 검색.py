from collections import defaultdict
from bisect import bisect_left


def solution(infos, queries):
    infomap = defaultdict(list)
    for info in infos:
        for i in range(16):
            temp = info.split()
            if i & 8 == 8:
                temp[0] = "-"
            if i & 4 == 4:
                temp[1] = "-"
            if i & 2 == 2:
                temp[2] = "-"
            if i & 1 == 1:
                temp[3] = '-'

            infomap["".join(temp[:-1])].append(int(temp[4]))
            # print("".join(temp[:-1]))

    for key in infomap.keys():
        infomap[key].sort()

    answer = []

    for query in queries:
        temp = query.split(" and ")
        final_temp = temp[-1].split()
        query_string = "".join(temp[:-1]) + final_temp[0]
        answer.append(
            len(infomap[query_string])
            - bisect_left(infomap[query_string], int(final_temp[1]))
        )

    return answer

# print(solution(["java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"], ["java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"]))