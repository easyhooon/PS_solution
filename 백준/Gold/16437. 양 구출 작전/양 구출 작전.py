import sys
sys.setrecursionlimit(10 ** 6)

si = sys.stdin.readline


def dfs(curr_node):  # v : 현재 노드
    result = 0

    for i in tree[curr_node]:
        result += dfs(i)

    if node[curr_node][0] == 'W':
        result -= node[curr_node][1]
        if result < 0:
            result = 0
    else:
        result += node[curr_node][1]
    return result


n = int(si())
tree = [[] for _ in range(n + 1)]
node = [[], [0, 0]]

for i in range(2, n + 1):
    animal, cnt, adj_node = si().strip().split()
    tree[int(adj_node)].append(i)
    node.append([animal, int(cnt)])

print(dfs(1))
