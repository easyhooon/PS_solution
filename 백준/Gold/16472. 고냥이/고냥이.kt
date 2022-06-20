import java.io.*
import java.util.*

//cnt를 int로 하면 안되고 전 문제 처럼 배열로 check 배열을 이용
// 투 포인터 - L 과 R의 방향, 누구를 고정하고 누구를 움직일지는 문제마다 다름 - 고정된 방식이 아니다!
//이 문제에서는 R를 고정하고 L를 거기에 맞출것 (이렇게 안했기에(L을 고정하고 R을 움직이려했기에) 어떻게 해도 안되었던거)
//ex) R을 4에 두고 L을 1에서 2로 옮겼을때 (땡김, a가 사라짐) -> R을 4로 고정했을때 인식가능한 가장 왼쪽 L
val scan = FastReader()
val sb = StringBuilder()

var N = 0
var kind = 0
var A = ""
lateinit var cnt: IntArray

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    A = scan.nextLine()
    cnt = IntArray(26)
}

fun add(x: Char) {  // x 라는 알파벳 추가
    cnt[x - 'a']++
    if (cnt[x - 'a'] == 1) // 새롭게 나타난 알파벳이라는 뜻
        kind++
}

fun erase(x: Char) {  // x 라는 알파벳 제거
    cnt[x - 'a']--
    if (cnt[x - 'a'] == 0) // 인식해야 하는 알파벳에서 빠지는 순간
        kind--
}

fun pro() {
    val len = A.length
    var ans = 0
    var R = 0
    var L = 0
    while (R < len) {

        // R 번째 문자를 오른쪽에 추가
        add(A[R])

        // 불가능하면, 가능할 때까지 L을 이동
        while (kind > N) {
            erase(A[L++])
        }

        // 정답 갱신
        ans = Math.max(ans, R - L + 1)
        R++
    }
    sb.append(ans)
    print(sb)
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
