//재귀로 순서 없이 m개 뽑고 뽑은 결과로 완전탐색(bfs)
import java.io.*
import java.lang.Math.abs
import java.lang.Math.min
import java.util.*

internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var scan = FastReader()
internal val sb = StringBuilder()
internal var N: Int = 0
internal var M: Int = 0
internal var ans: Int = Int.MAX_VALUE

lateinit var chicken: ArrayList<Pair<Int,Int>>
lateinit var selected: Array<Int>
// 2차원 Array 선언
lateinit var board: Array<Array<Int>>

fun main() {
    solve()
}

fun solve() {
    input()
    recursiveFunc(1)

    sb.append(ans)
    print(sb.toString())
}

fun input() {
    N = scan.nextInt()
    M = scan.nextInt()

    chicken = arrayListOf()
    chicken.add(Pair(0, 0))
    board = Array(N+1){Array(N+1){ 0 } }
    //selected = Array<Pair<Int,Int>>는 크기와 초기값을 할당해줘야함
    //selected = arrayOf<Pair<Int,Int>>() 는 노팔요
    selected = Array(15){ 0 }


    for (i in 1..N) {
        for(j in 1.. N) {
            board[i][j] = scan.nextInt()
            if(board[i][j] == 2){
                //치킨집
                chicken.add(Pair(i, j))
                //println("$i $j")
            }
        }
    }
    //println(chicken.size)
}

// 중복을 허용하지 않음
// 비내림차순
// 만약 M 개를 전부 고름 => 조건에 맞는 탐색을 한 가지 성공한 것
// 아직 M 개를 고르지 않음  => k 번째부터 M번째 원소를 조건에 맞게 고르는 방법을 시도
fun recursiveFunc(k: Int) {
    if (k == M + 1) {
        // 다 골랐다!
        // selected[1..M] 배열이 새롭게 탐색된 결과
        //println(getChickenDistanceSum())
        ans = min(ans, getChickenDistanceSum())
    } else {
        for (cand in 1 until chicken.size) {
            selected[k] = cand

            if(selected[k-1] < selected[k]){
                // k+1 번 ~ M 번 을 모두 탐색하는 일을 해야하는 상황
                recursiveFunc(k+1)
                // 탐색이 끝난 뒤에 k 번째 원소에 기록을 남겨줄 필요가 없기 때문에
                selected[k] = 0
            }
        }
    }
}

fun getChickenDistanceSum(): Int {
    var sum = 0
    for(i in 1..N){
        for(j in 1..N) {
            if(board[i][j] == 1) {
                var chickenDistance = Int.MAX_VALUE
                for(k in 1..M) {
                    chickenDistance = min(chickenDistance,
                        abs(i - chicken[selected[k]].first) + abs(j - chicken[selected[k]].second))
                    //println("$i $j 의 ${chicken[selected[k]].first} , ${chicken[selected[k]].second} 까지의 거리 chickenDistance: $chickenDistance")
                }
                sum += chickenDistance
            }
        }
    }
    return sum
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