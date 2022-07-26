import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

//힌트
//1. 휴게소와 휴게소 사이에 새로운 휴게소를 설치할때, 새로운 휴게소 기준으로 왼쪽 휴게소로 부터 "얼마 만큼 떨어진 휴게소"를 지을지 거리를 정하고,
// 그 거리로 설치하게 되면 "총 몇 개의 휴게소룰 새로설치"하게 되는지 계산을해서, 새로 설치하는 휴게소가 제한된 개수보다 적거나 같으면 더 작은 간격으로 설치가 가능하다.
// 반대로 새로 설치하는 휴게소 개수가 제한된 개수보다 많으면 더 멀리 떨어지게 휴게소를 만들 수 있다.

val br = BufferedReader(InputStreamReader(System.`in`))
val sb = StringBuilder()
var N = 0
var M = 0
var length = 0
lateinit var v: IntArray
var ans = 0

fun main() {
    input()
    solve()
}

fun input() {
    var st = StringTokenizer(br.readLine())

    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    length = st.nextToken().toInt()

    //python 처럼 입력받기
    //val (N, M, length) = br.readLine().split(' ').map { it.toInt() }
    v = IntArray(N + 2)

    st = StringTokenizer(br.readLine())

    for (i in 1 until N + 1) {
        v[i] = st.nextToken().toInt()
    }
}

fun solve() {
    v[N + 1] = length
    Arrays.sort(v)
    //println(v.joinToString())

    val answer = binarySearch()

    sb.append(answer).append("\n")
    print(sb.toString())
}

fun binarySearch(): Int {
    var left = 1
    var right = length - 1
    while (left <= right) {
        val mid = (left + right) / 2
        // 현재 middle 초과하여 거리 차이가 가능하면, 더 큰 최대값 찾기
        if (determination(mid)) {
            ans = mid
            //left = mid + 1
            right = mid - 1
        } else {
            //right = mid - 1
            left = mid + 1
        }
    }
    return ans
    //return left
}

fun determination(distance: Int): Boolean {
    var cnt = 0
    for (i in 0 until N + 1) {
        val d = v[i + 1] - v[i]
        var temp: Int
        if (d / distance > 0) {
            // 휴게소간의 거리가 새로 설치한 distance 로 나누었을때 딱 나눠떨어지면,
            // 나눈값에 -1개 한 수만큼 설치가 가능하다.
            if (d % distance == 0) {
                temp = d / distance - 1
            } else {
                temp = d / distance
            }
            cnt += temp
        }
    }

    // 휴게소를 M 개 보다 더 많이 세울 수 있을 경우
    // 휴게소를 M 개 이상 세울 수 있는 경우
    return cnt <= M
    //return cnt > M
}