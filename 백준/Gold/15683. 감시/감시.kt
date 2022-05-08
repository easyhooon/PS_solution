import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

var N: Int = 0
var M: Int = 0
var ret: Int = Int.MAX_VALUE

// cctv 의 개수
var cnt: Int = 0

var map = Array(10) { IntArray(10) }
var visit = Array(10) { BooleanArray(10) }

var cctv: ArrayList<CCTV> = ArrayList()

var rot_size: IntArray = intArrayOf(4, 2, 4, 4, 1)

data class CCTV(var x: Int, var y: Int, val type: Int)

fun main() {
    solve()
}

fun solve() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    M = scan.nextInt()

    for (i in 1..N) {
        for (j in 1..M) {
            map[i][j] = scan.nextInt()

            if (map[i][j] != 0 && map[i][j] != 6) {
                // 나머지 연산을 위함
                cctv.add(CCTV(i, j, map[i][j] - 1))
                cnt++
            }
        }
    }
//    for(i in 1..N) {
//        for(j in 1.. M) {
//            print("${map[i][j]} ")
//        }
//        println()
//    }
}

fun pro() {
    dfs(0)
    print(ret)
}

//반복 작업의 형태는 bfs or dfs(재귀)로 구현
fun dfs(cctv_index: Int) {
    if (cctv_index == cnt) {
        // 다 골랐다!
        // 감시 가능 구역 count
        mapCount()
        return

    } else {
        val backup = Array(10) { IntArray(10) }
        val type = cctv[cctv_index].type
        //println(type)
        for (dir in 0 until rot_size[type]) {
            //map backup
            copyMap(backup, map)
            //map update
            when (type) {
                0 -> {
                    update(dir, cctv[cctv_index])
                }

                1 -> {
                    update(dir, cctv[cctv_index])
                    update(dir + 2, cctv[cctv_index])
                }

                2 -> {
                    update(dir, cctv[cctv_index])
                    update(dir + 1, cctv[cctv_index])
                }

                3 -> {
                    update(dir, cctv[cctv_index])
                    update(dir + 1, cctv[cctv_index])
                    update(dir + 2, cctv[cctv_index])
                }

                4 -> {
                    update(dir, cctv[cctv_index])
                    update(dir + 1, cctv[cctv_index])
                    update(dir + 2, cctv[cctv_index])
                    update(dir + 3, cctv[cctv_index])
                }
            }
            dfs(cctv_index + 1)
            //backtracking
            copyMap(map, backup)
        }
    }
}

fun mapCount() {
    var cnt = 0
    for (i in 1..N) {
        for (j in 1..M) {
            //print("${map[i][j]} ")
            if (map[i][j] == 0) {
                cnt++
            }
        }
        //println()
    }
    ret = ret.coerceAtMost(cnt)
    //println()
}

// kotlin 의 함수의 매개변수는 항상 val 이다.
fun update(dir: Int, cctv: CCTV) {
    var dir = dir
    dir = (dir % 4)

    //시계 방향
    when (dir) {
        0 -> {
            for (y in cctv.y downTo 1) {
                if (map[cctv.x][y] == 6) break
                map[cctv.x][y] = -1
            }
        }

        1 -> {
            for (x in cctv.x..N) {
                if (map[x][cctv.y] == 6) break
                map[x][cctv.y] = -1
            }
        }

        2 -> {
            for (y in cctv.y..M) {
                if (map[cctv.x][y] == 6) break
                map[cctv.x][y] = -1
            }
        }

        3 -> {
            for (x in cctv.x downTo 1) {
                if (map[x][cctv.y] == 6) break
                map[x][cctv.y] = -1
            }
        }
    }
}

fun copyMap(map: Array<IntArray>, backup: Array<IntArray>) {
    for (i in 1..N) {
        for (j in 1..M) {
            map[i][j] = backup[i][j]
        }
    }
}

class FastReader {
    var br: BufferedReader
    var st: StringTokenizer? = null

    constructor() {
        br = BufferedReader(InputStreamReader(System.`in`))
    }

    constructor(s: String?) {
        br = BufferedReader(FileReader(File(s)))
    }

    operator fun next(): String {
        while (st == null || !st!!.hasMoreElements()) {
            try {
                st = StringTokenizer(br.readLine())
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return st!!.nextToken()
    }

    fun nextInt(): Int {
        return next().toInt()
    }

    fun nextLong(): Long {
        return next().toLong()
    }

    fun nextDouble(): Double {
        return next().toDouble()
    }

    fun nextLine(): String {
        var str = ""
        try {
            str = br.readLine()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return str
    }
}
