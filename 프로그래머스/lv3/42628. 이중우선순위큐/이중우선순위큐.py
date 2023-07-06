import heapq

# len(operations) <= 1,000,000
# 큐가 비어있으면 [0,0] 을 return 
def solution(operations):
    answer = []
    
    max_pq = []
    min_pq = []
    
    # O(n) or O(nlogn) 으로 풀어야 함 
    for op in operations:
        if op.startswith('I'):
            num = int(op.split(' ')[1])
            heapq.heappush(max_pq, -num)
            heapq.heappush(min_pq, num)
            
        elif op.startswith('D'):
            # "-1" 이거 int 씌우면 -1 되나 -> 됨
            num = int(op.split(' ')[1])
            # 최댓값 삭제
            if num == 1:
                if max_pq and min_pq:
                    heapq.heappop(max_pq)
                    if not max_pq:
                        min_pq = []
                    
                
            # 최솟값 삭제
            else:
                if min_pq and min_pq:
                    heapq.heappop(min_pq)
                    if not min_pq:
                        max_pq = []
                
        # print(max_pq)
        # print(min_pq)
        # print()
            
    if max_pq and min_pq:
        answer = [-max_pq[0], min_pq[0]]
        
    else:
        answer = [0, 0]
        
    
    return answer