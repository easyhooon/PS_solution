# 이진트리를 구성하는 각 노드의 x, y 좌표를 받아서
# 이를 통해서 이진트리를 구성하고,
# 그 이진 트리의 전위 순회, 후위 순회한 결과를 2차원 배열에 순서대로 담아 return
# 좌표를 통해 트리를 구축하는게 어렵다
import sys

sys.setrecursionlimit(10 ** 6)

# 트리 구축 
def add_node(parent, child, tree):
    if tree[child][0] < tree[parent][0]:
        if tree[parent][1] is None:
            tree[parent][1] = child
        else:
            add_node(tree[parent][1], child, tree)

    else:
        if tree[parent][2] is None:
            tree[parent][2] = child
        else:
            add_node(tree[parent][2], child, tree)


# 전위 탐색
def pre_order(node, tree, order):
    # None 이면 존재하지 않으므로 종료
    if node is None:
        return

    order.append(node)
    pre_order(tree[node][1], tree, order)
    pre_order(tree[node][2], tree, order)


# 후위 탐색
def post_order(node, tree, order):
    if node is None:
        return

    post_order(tree[node][1], tree, order)
    post_order(tree[node][2], tree, order)
    order.append(node)


def solution(nodeinfo):
    info = {idx + 1: (x, y) for idx, (x, y) in enumerate(nodeinfo)}
    # print(info)
    nodes = sorted(list(info.keys()), key=lambda x: (-info[x][1], info[x][0]))
    # print(nodes)

    tree = {node: [info[node][0], None, None] for node in nodes}
    # print(tree)

    root = nodes[0]
    for node in nodes[1:]:
        #print(node)
        add_node(root, node, tree)

    # print(tree)

    pre_order_list = []
    post_order_list = []

    pre_order(root, tree, pre_order_list)
    post_order(root, tree, post_order_list)

    return [pre_order_list, post_order_list]
