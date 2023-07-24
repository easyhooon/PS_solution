def solution(sizes):
    answer = 0
    
    for size in sizes:
        size.sort()
        
    print(sizes)

    max_width = 0
    for i in range(len(sizes)):
        max_width = max(max_width, sizes[i][0])
        
    max_height = 0
    for i in range(len(sizes)):
        max_height = max(max_height, sizes[i][1])
        
    answer = max_width * max_height
    
    return answer