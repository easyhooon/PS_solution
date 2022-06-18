import java.io.*
import java.util.*

val scan = FastReader()
val sb = StringBuilder()


//수의 위치가 다르면 값이 같아도 다른 수이다.
//내림차순으로 정렬해서 풀면 더 쉽지않을까?
//L의 위치에 있는 수를 R이 계속 움직이며 다른 두개의 수의 합으로 표현할 수 있으면 sum++
//R은 하난데 두개의 수를 컨트룰?
//두용액 문제를 복기
//정렬 -> 타겟 숫자 결정 -> 다른 두개의 숫자 결정
//target_idx 번째 원소가 서로 다른 두 수의 합으로 표현되는가를 함수 모듈화
//target_idx 의 숫자로 target 을 만들지 않도록 주의
//이러면 n 제곱아님?
var N = 0
var ans = 0
lateinit var arr: IntArray

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
    arr = IntArray(N + 1)
    for (i in 1..N) {
        arr[i] = scan.nextInt()
    }
}

fun pro() {
    //아 그거같은데 배열에 음수가 포함되어서 맨 앞에 있던 0이 영향을 받음
    //idx 를 지정해서 배열 정렬해야! 
    Arrays.sort(arr, 1, N+1)
    //println(arr.joinToString())

    for (i in 1..N) {
        //i번째 원소가 서로 다른 두 수의 합으로 표현이 되는가?
        if (like(i)) {
            ans++
        }
    }

    sb.append(ans)
    println(sb)
}

//target idx 번째 원소가 서로 다른 두 수의 합으로 표현이 되는가?
//반례
// 3
// 0 0 0
// 1 2 일때 되는데 cnt 되지 않음
// 양쪽으로 체크 어떻게함
fun like(target_idx: Int): Boolean {
    var L = 1
    var R = N
    var target = arr[target_idx]

    while (L < R) {
//        if(target == arr[L] + arr[R]) {
//            //target_idx 를 통해 답을 구하면 안된다.
//            //한꺼번에 묶어서 처리하면 안됨
////            if(L != target_idx && R != target_idx) {
////                println("$target_idx $L $R")
////                return true
////            }
//            if(L == target_idx) {
//                L++
//                continue
//            }
//            if(R == target_idx) {
//                R--
//                continue
//            }
//            println("$target_idx $L $R")
//            return true
        if (L == target_idx) L++
        else if (R == target_idx) R--
        else {
            if (arr[L] + arr[R] == target) {
                //println("$target_idx $L $R")
                return true
            }
            if (target > arr[L] + arr[R]) L++ else R--
        }
    }
    return false
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
