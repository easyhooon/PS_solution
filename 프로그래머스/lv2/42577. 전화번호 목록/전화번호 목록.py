# 다른 번호의 접두어인 경우가 있으면 false
# n <= 1,000,000 -> 2차원 포문으로 풀이 불가능, nlogn
# 같은 값이 들어있지 않음 -> 굳이 Counter 안써도 될듯
# slice 도 O(n) 이라서 n^2 임 
# 무조건 첫번째 번호랑만 비교하는 문제가 아님 (문제 잘못 이해)
from collections import Counter

def solution(phone_book):
    # prefix = phone_book[0]
    # counter = Counter(phone_book)
    # print(counter)
    n = len(phone_book)
    phone_book.sort()
    
    # for key in counter.keys():
    #     if prefix in key:
    #         if prefix != key:
    #             return False
    
    for i in range(1, n):
        if phone_book[i].startswith(phone_book[i-1]):
            return False
    return True