//파일 개수 50000개 n^2 하면 터짐
import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()

var N = 0
//lateinit var file: StringArray
lateinit var files: ArrayList<String>
lateinit var extensions: ArrayList<String>

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    //files = ArrayList<String>(N)
    files = ArrayList<String>()
    extensions = ArrayList<String>()
    for(i in 0 until N) {
//        val temp = scan.nextLine()
//        println(temp)
        //files[i] = scan.nextLine()
        files.add(scan.nextLine())
        val ext  = files[i].split(".")[1]
        extensions.add(ext)
    }
}

fun pro() {
    extensions.sort()
//    println(extensions)
    var cnt = 0
    var now = extensions[0]
    for(i in 0 until extensions.size) {
        if(now == extensions[i]) {
            cnt++
        } else {
            println("$now $cnt")
            now = extensions[i]
            cnt = 1
        }
        if (i == extensions.size-1) {
            println("$now $cnt")
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
