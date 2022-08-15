fun main() {
    val (n, m, b) = readln().split(" ").map { it.toInt() }
    val map = Array(n) { IntArray(m) }
    for (i in 0 until n) {
        map[i] = readln().split(" ").map { it.toInt() }.toIntArray()
    }
    var totalBlocks = b

    for(i in 0 until n) {
        for(j in 0 until m) {
            totalBlocks += map[i][j]
        }
    }

    // 한 칸의 높이
    var height = totalBlocks / (n * m)
    if (height > 256) {
        height = 256
    }
    var minTime = Int.MAX_VALUE
    var finalHeight = height
    while (height >= 0) {
        var currentTime = 0
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (map[i][j] <= height) {
                    currentTime += height - map[i][j]
                } else {
                    currentTime += 2 * (map[i][j] - height)
                }
            }
        }

        //if(currentTime < minTime 으로 하면 맞음 <= 이걸로 하면 틀림
        if (currentTime < minTime) {
            minTime = currentTime
            finalHeight = height
            //println("$minTime $finalHeight")
        }
        height--
    }
    println("$minTime $finalHeight")
}
