// 백준에서 분명 풀었던 문젠데..
// 완탐 가능이다. 완탐 + 백트래킹 같은데
// 전역변수가 없다는게 참 힘드네
class Solution {
    var cnt = 0
    lateinit var selected: IntArray

    fun solution(numbers: IntArray, target: Int): Int {
        selected = IntArray(numbers.size)
        recursiveFuc(0, numbers, target)
        var answer = cnt
        return answer
    }

    fun recursiveFuc(index: Int, numbers: IntArray, target: Int) {
        if (index == numbers.size) {
            //println(selected.joinToString())
            //println(numbers.joinToString())
            var sum = 0
            for (i in 0..index-1) {
                // println(i)
                // 인덱스가 안맞는듯
                if (selected[i] == 0) {
                    sum -= numbers[i]
                } else {
                    sum += numbers[i]
                }
            }
            //println(sum)
            //println(target)
            if (sum == target) {
                cnt += 1
            }
            return
        } else {
            //연산자 뽑기
            for (cand in 0..1) {
                selected[index] = cand
                recursiveFuc(index + 1, numbers, target)
                selected[index] = 0
            }
        }
    }
}