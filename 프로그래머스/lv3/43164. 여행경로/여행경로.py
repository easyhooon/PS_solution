# 주어진 항공권은 모두 사용해야 합니다.
# 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return

# 문자열로 탐색해야 해서 curr_num 이 아니다
def dfs(curr_dest, result, tickets, visited):
    if len(result) == len(tickets) + 1:
        return result

    for i in range(len(tickets)):
        if tickets[i][0] == curr_dest and not visited[i]:
            visited[i] = True
            result.append(tickets[i][1])
            ret = dfs(tickets[i][1], result, tickets, visited)
            # 이 코드가 왜 존재해야하는지 모르겠는데
            if ret:  # 만약에 경로가 존재하면 반환
                return ret
            dfs(tickets[i][1], result, tickets, visited)
            result.pop()
            visited[i] = False


# dfs 로 풀자
def solution(tickets):
    # 단방향 그래프를 굳이 만들 필요가 없을 것 같기도 하고, 문자열이라 인덱스접근도 안되고
    # for (start, end) in tickets
    # 어떤 자료구조를 사용해서 저장하지 그러면
    # 인접리스트가 맞는데 음 문자열인데
    # 그냥 tickets[i][0], tickets[i][1] 등으로 접근해야할듯?
    # 공항의 수 10,000개
    # 이거 배열 복사 관련 문제이다

    n = len(tickets)
    visited = [False] * n
    tickets.sort()
    # 처음에 result 배열 내 "ICN" 을 넣어줘야 한다
    result = ["ICN"]
    answer = []

    # print(tickets)

    answer = dfs("ICN", result, tickets, visited)

    return answer
