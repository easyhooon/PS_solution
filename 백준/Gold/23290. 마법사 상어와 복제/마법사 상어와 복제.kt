import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

data class Smell(var x: Int, var y: Int, var turn: Int)
data class Fish(var x: Int, var y: Int, var d: Int, var cnt: Int)

// 4 x 4 격자지만 넉넉하게 6 x 6 (앞 - 뒤 , 위 - 아래), 새번째 인자는 방향(현재 어느방향을 바라보고 있는지)
var map = Array(6) { Array(6) { IntArray(9) } }
// 맵 복원용
var copyOfMap = Array(6) { Array(6) { IntArray(9) } }
var fishCount = Array(6) { IntArray(6) }
// 상어 맵
var sMap = Array(6) { IntArray(6) }

// 물고기 움직임 방향
var fx = intArrayOf(0, 0, -1, -1, -1, 0, 1, 1, 1)
var fy = intArrayOf(0, -1, -1, 0, 1, 1, 1, 0, -1)

// 상어 움직임 방향
var sx = intArrayOf(0, -1, 0, 1, 0)
var sy = intArrayOf(0, 0, -1, 0, 1)

// 상어 좌표
var shark = IntArray(2)
var max = IntArray(2) // 상어가 가장 "많은" 물고기를 먹는 방법, max[0]:path, max[1]:numberOfKill

var fishes: Queue<Fish> = LinkedList()
var smell: Queue<Smell> = LinkedList() // 2턴이 지나면 지우기 위해

var numberOfFish: Int = 0
var numberOfMagic: Int = 0

fun main() {
    solve()
}

fun solve() {
    input()
    pro()
}

fun input() {
    numberOfFish = scan.nextInt()
    numberOfMagic = scan.nextInt()

    //물고기 정보
    for (i in 1..numberOfFish) {
        val x = scan.nextInt()
        val y = scan.nextInt()
        val d = scan.nextInt()

        map[x][y][d]++
    }

    //상어 정보
    // x
    shark[0] = scan.nextInt()
    // y
    shark[1] = scan.nextInt()
}

fun pro() {
    for (turn in 0 until numberOfMagic) {
        // 1. 복제 마법 세팅
        // copyOfMap 에 사전 시점의 물고기 위치 저장
        setMagic()

        // 2. 모든 물고기 한칸 이동(BFS)
        moveFish()

        // 3. 가장 많은 물고리를 제외시키는 방향을 찾는다. (DFS, 중복 순열)
        max[0] = 0; max[1] = -1
        getWay(0, 1, 0, shark[0], shark[1])

        // 4. 해당 방향대로 상어 연속 3칸 이동
        moveShark(turn)

        // 5. 두번 전 연습에서 생긴 냄새 사라짐
        removeSmell(turn)

        // 6. 맨 처음 캐스팅했던 마법이 시전됨
        doMagic()
    }

    // 7. 현재 남아있는 물고기 수를 count
    print(countNumberOfFish())
}

/**
 * 1. 복제마법 시전
 * copyOfMap 에 map 복사
 * fishes 큐에 map 에 있는 모든 물고기 정보를 넣는다.
 */
fun setMagic() {
    for (x in 1..4) {
        for (y in 1..4) {
            for (d in 1..8) {
                copyOfMap[x][y][d] = map[x][y][d]
                if (map[x][y][d] == 0) continue
                fishes.add(Fish(x, y, d, map[x][y][d]))
            }
        }
    }
}

/**
 * 2. 모든 물고기 한칸 이동 (BFS)
 * 상어나 물고기냄새가 있거나, 범위외로 갈 수 없다.
 */
fun moveFish() {
    while (!fishes.isEmpty()) {
        val fish = fishes.poll()
        val nCnt = fish.cnt
        var dir = fish.d

        for (d in 0..7) { // 현재 방향으로부터 팔방탐색
            val nfx = fish.x + fx[dir]
            val nfy = fish.y + fy[dir]

            if (fishCanGo(nfx, nfy)) {
                // 물고기 이동
                map[fish.x][fish.y][fish.d] -= nCnt
                map[nfx][nfy][dir] += nCnt
                fish.x = nfx
                fish.y = nfy
                fish.d = dir
                break
            }
            dir -= 1 // 0~9까지 나와야함
            if (dir < 1) {
                dir = 8
            }
        }
    }
}

/** 범위 밖을 벗어나는지 검사  */
fun inRange(nfx: Int, nfy: Int): Boolean {
    return nfx >= 1 && nfy >= 1 && nfx <= 4 && nfy <= 4
}

fun fishCanGo(x: Int, y: Int): Boolean {
    return inRange(x, y) && (x != shark[0] || y != shark[1]) && sMap[x][y] == 0
}

/**
 * 상어가 현재 path 로 간다면 물고기 몇마리 죽일 수 있는지 리턴
 */
fun getNumberOfKilledFish(depth: Int, _path: Int): Int {
    //(_path)
    // 갔다가 다시 되돌아올수도 있으므로 물고기 죽이면서 가야함. 안그럼 중복 카운트 됨
    // 그러므로 맵 복사하여 한다.
    for (x in 1..4) {
        for (y in 1..4) {
            fishCount[x][y] = 0
            for (d in 1..8) {
                fishCount[x][y] += map[x][y][d]
            }
        }
    }
    var killedFish = 0
    var dsx = shark[0]
    var dsy = shark[1]
    val path = _path.toString()

    for (i in 0..2) { // 3번만 이동
        val dir = path[i] - '0'
        dsx += sx[dir]
        dsy += sy[dir]

        if (!inRange(dsx, dsy)) return -1 // 잘못된 path

        for (d in 1..8) {
            killedFish += fishCount[dsx][dsy]
            fishCount[dsx][dsy] = 0
        }
    }
    return killedFish
}

/**
 * 3. 가장 많은 물고기를 제외시키는 방향을 찾는다. (DFS,중복조합)
 * 상어는 물고기를 가장많이 제외시킬 수 있는 방법으로 이동 (DFS,중복조합)
 */
fun getWay(depth: Int, start: Int, path: Int, x: Int, y: Int) {
    if (depth == 3) {
        // 현재 path 로 몇마리 죽일 수 있는지 시뮬레이션
        val numberOfKill = getNumberOfKilledFish(0, path)
        // 상어가 가장 "많은" 물고기를 먹는 방법, max[0]:path, max[1]:numberOfKill
        // max[0]:path, max[1]:numberOfKill
        if (max[1] < numberOfKill) {
            // 기존보다 더 많이 죽일 수 있다면 업데이트
            max[0] = path
            max[1] = numberOfKill
        }
        return
    }

    // 중복순열
    for (i in 1..4) {
        val nx = x + sx[i]
        val ny = y + sy[i]
        if (!inRange(nx, ny)) continue
        getWay(depth + 1, i, path * 10 + i, nx, ny)
    }
}

/**
 * 4. 해당 방향대로 상어 연속 3칸 이동
 * 상어가 지나가는 곳에 있는 물고기는 다죽음
 * 죽은 물고기는 냄새 남김
 */
fun moveShark(turn: Int) {
    // 상어가 가장 "많은" 물고기를 먹는 방법, max[0]:path, max[1]:numberOfKill
    //max[0] = path 이므로 이거대로 이동
    val path = max[0].toString()

    var dsx = shark[0]
    var dsy = shark[1]

    for (i in 0..2) { // 3번 이동
        val dir = path[i] - '0'

        dsx += sx[dir]
        dsy += sy[dir]
        // 모든 방향의 물고기 다 죽임

        var isKill = false
        for (d in 1..8) {
            if (map[dsx][dsy][d] != 0) {
                map[dsx][dsy][d] = 0
                isKill = true
            }
        }
        if (isKill) {
            sMap[dsx][dsy]++
            smell.add(Smell(dsx, dsy, turn)) // 물고기 죽었을때만 넣어줘야 함.
        }
    }

    // shark 위치 업데이트
    shark[0] = dsx
    shark[1] = dsy
}

/**
 * 5. 두번 전 연습에서 생긴 냄새 없애기
 */
fun removeSmell(turn: Int) {
    while (!smell.isEmpty() && smell.peek().turn == turn - 2) {
        val (x, y) = smell.poll()
        sMap[x][y]--
    }
}

/**
 * 6. 맨처음 캐스팅했던 마법이 시전됨
 * copyOfMagic 에 있는 물고기를 map에 더함
 */
fun doMagic() {
    for (i in 1..4) {
        for (j in 1..4) {
            for (d in 1..8) {
                map[i][j][d] += copyOfMap[i][j][d]
            }
        }
    }
}

/**
 * 7. 현재 남아있는 물고기의 수를 count
 * */

fun countNumberOfFish(): Int {
    var numberOfFish = 0
    for (i in 1..4) {
        for (j in 1..4) {
            for (d in 1..8) {
                numberOfFish += map[i][j][d]
            }
        }
    }
    return numberOfFish
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
