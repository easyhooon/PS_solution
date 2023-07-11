import java.io.*
import java.lang.Integer.max
import java.util.*

// 최소 시간 구하기 도움받음
internal val scan = FastReader()
internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0
internal var M: Int = 0
internal var W: Int = 0
internal var tc: Int = 0
lateinit var adj: ArrayList<ArrayList<Int>>
// degree: 차수, indegree: 노드로 들어오는 간선의 개수
lateinit var indegree: IntArray
lateinit var t:IntArray
lateinit var t_done:IntArray

fun main() {
    solve()
}

fun solve() {
    tc = scan.nextInt()
    repeat(tc) {
        input()
        solution()
    }
}

fun input() {
    N = scan.nextInt()
    M = scan.nextInt()

    // 테스트 케이스가 존재하는 문제이므로 "배열 초기화"에 유의하자

    // 각 정점에 대해서 인접 리스트 생성
    adj = ArrayList<ArrayList<Int>>()
    indegree = IntArray(N+1)
    t = IntArray(N+1)
    t_done = IntArray(N+1)


    for (i in 0..N) {
        adj.add(ArrayList())
    }

    for (i in 1..N) {
        t[i] = scan.nextInt()
    }

    // 간선 입력받기
    // V = E + 1
    for (i in 1..M) {
        val x = scan.nextInt()
        val y = scan.nextInt()

        // 위상정렬은 방향성이 있는 그래프이므로 한쪽으로만 이동가능, 한쪽만 연결
        adj[x].add(y)
        // indegree 계산하기
        indegree[y]++
    }

    // 승리하기 위해 건설해야 할 건물 번호 W
    W = scan.nextInt()
}

fun solution() {
    val q = LinkedList<Int>()
    // 제일 앞에 "정렬될 수 있는" 정점 찾기
    for (i in 1..N)
        if (indegree[i] == 0) {
            q.add(i)
            t_done[i] = t[i]
        }

    // 정렬될 수 있는 정점이 있다면?
    // 1. 정렬 결과에 추가하기
    // 2. 정점과 연결된 간선 제거하기
    // 3. 새롭게 "정렬 될 수 있는" 정점 Queue 에 추가하기
    while (!q.isEmpty()) {
        val x = q.poll()
        //sb.append(x).append(' ')
        for (y in adj[x]) {
            indegree[y]--
            if (indegree[y] == 0) {
                // 차수가 0으로 들어오는 순간 Queue 에 추가하기
                q.add(y)
            }
            // 더 큰 값으로 갱신
            t_done[y] = max(t_done[y], t_done[x] + t[y])

        }
    }

    // W 를 건설완료한느데 드는 최소 시간 출력
    sb.append(t_done[W])
    println(sb)
    sb.clear()
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