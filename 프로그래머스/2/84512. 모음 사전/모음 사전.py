def solution(word):
    vowels = ['A', 'E', 'I', 'O', 'U']
    multipliers = [781, 156, 31, 6, 1]  # 5^4, 5^3, 5^2, 5^1, 5^0
    
    answer = 0
    
    for i in range(len(word)):
        answer += vowels.index(word[i]) * multipliers[i] + 1
        
    return answer