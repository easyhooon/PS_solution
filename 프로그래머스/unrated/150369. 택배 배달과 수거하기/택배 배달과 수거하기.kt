class Solution {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        val reversed_deliveries = deliveries.reversed()
        val reversed_pickups = pickups.reversed()
        
        
        var answer: Long = 0
        
        var haveToDeli = 0
        var haveToPick = 0
        
        for (i in 0 until n) {
            haveToDeli += reversed_deliveries[i]
            haveToPick += reversed_pickups[i]
            
            while(haveToDeli > 0 || haveToPick > 0) {
            haveToDeli -= cap
            haveToPick -= cap
            answer += (n - i) * 2
            }
        }
        
        return answer
    }
}