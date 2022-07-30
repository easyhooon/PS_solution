import java.io.*
import java.util.*
import kotlin.collections.ArrayList

val scan = FastReader()
val sb = StringBuilder()

var N = 0
var M = 0
var K = 0
var X = 0

lateinit var graph: Array<ArrayList<Int>>
lateinit var check: BooleanArray
lateinit var dist: IntArray
var answer = ArrayList<Int>()

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    M = scan.nextInt()
    K = scan.nextInt()
    X = scan.nextInt()

    graph = Array(N+1) { ArrayList<Int>()}
    check = BooleanArray(N+1)
    dist = IntArray(N+1)

    for(i in 1..M) {
        val A = scan.nextInt()
        val B = scan.nextInt()
        graph[A].add(B)
    }

    //println(graph.contentToString())
}

fun pro() {
    //dfs(X, 0)
    bfs(X)
    //answer.sort()
    //println(dist.joinToString())
    for(i in 1 until dist.size) {
        if (dist[i] == K) {
            answer.add(i)
        }
    }

    if (answer.isEmpty()) {
        println(-1)
    }
    else {
        for(element in answer) {
            println(element)
        }
    }
}

//fun dfs(start: Int, depth: Int) {
//    if(depth == K && !check[start]) {
//        answer.add(start)
//        return
//    }
//
//    for(i in graph[start]) {
//        check[i] = true
//        dfs(i, depth+1)
//    }
//}

fun bfs(start: Int) {
    val q = LinkedList<Int>()
    q.add(start)
    check[start] = true
    while(!q.isEmpty()) {
        val cur = q.poll()
        for(next in graph[cur]) {
            if(!check[next]) {
                check[next] = true
                q.add(next)
                dist[next] = dist[cur] + 1
            }
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