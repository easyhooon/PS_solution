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
            var sum = 0
            for (i in 0..index-1) {
                if (selected[i] == 0) {
                    sum -= numbers[i]
                } else {
                    sum += numbers[i]
                }
            }
            if (sum == target) {
                cnt += 1
            }
            return
        } else {
            for (cand in 0..1) {
                selected[index] = cand
                recursiveFuc(index + 1, numbers, target)
                selected[index] = 0
            }
        }
    }
}