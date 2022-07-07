import java.io.*
import java.util.*

internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var N: Int = 0
internal var MIN: Int = Int.MAX_VALUE
internal var MAX: Int = Int.MIN_VALUE
internal var numbers: IntArray = IntArray(20) { 0 }
// operator order (연산자 순서를 저장)
internal var order: IntArray = IntArray(20) { 0 }
internal var operators: IntArray = IntArray(10) { 0 }

fun main() {
    val scan = FastReader()
    N = scan.nextInt()

    for(i in 1..N) {
        numbers[i] = scan.nextInt()
    }

    for(i in 1..4) {
        operators[i] = scan.nextInt()
    }

    recursiveFunc(1)

    bw.write("$MAX \n$MIN")

    bw.flush()
    bw.close()
}

// 완성된 식에 맞게 계산을 해서 정답에 갱신하는 작업
fun calculate() {
    var value = numbers[1]
    for(i in 1..N-1) {
        // value, order[i], numbers[i+1]
        when(order[i]) {
            1 -> value += numbers[i+1]
            2 -> value -= numbers[i+1]
            3 -> value *= numbers[i + 1]
            4 -> value /= numbers[i + 1]
        }
    }
    MAX = kotlin.math.max(value, MAX)
    MIN = kotlin.math.min(value, MIN)
}

// 연산자 순서 나열
// 만약 M 개를 전부 고름 => 조건에 맞는 탐색을 한 가지 성공한 것
// 아직 M 개를 고르지 않음  => k 번째부터 M번째 원소를 조건에 맞게 고르는 방법을 시도
fun recursiveFunc(k: Int) {
    if (k == N) {
        // 다 골랐다!
        // 완성된 식에 맞게 계산을 해서 정답에 갱신하는 작업 (함수화)
        calculate()
    } else {
        // k 번째 연산자는 무엇을 선택할 것인가?
        for (cand in 1..4) {
            if(operators[cand] != 0) {
                operators[cand]--
                order[k] = cand
                // k+1 번 ~ M 번 을 모두 탐색하는 일을 해야하는 상황
                recursiveFunc(k+1)
                // 탐색이 끝난 뒤에 k 번째 원소에 기록을 남겨줄 필요가 없기 때문에
                operators[cand]++ // 백트래킹이므로 변화를 준것은 모두 원상 복구! 
                order[k] = 0
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
