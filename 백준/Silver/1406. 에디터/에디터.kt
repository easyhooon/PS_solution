// 두개의 스택을 사용하여 (커서 기준으로 left top, right top) 구현해보자
import java.util.*

val sb = StringBuilder()
var str = ""
var N = 0

fun main() {
    str = readln()
    N = readln().toInt()

    solution()
}

fun solution() {
    val lst = Stack<Char>()
    val rst = Stack<Char>()

    for (e in str) {
        lst.push(e)
    }
    for (i in 1..N) {
        var input = readln().split(' ')
        when (input[0]) {
            "L" -> {
                if (lst.isNotEmpty()) {
                    rst.push(lst.pop())
                }
            }

            "D" -> {
                if (rst.isNotEmpty()) {
                    lst.push(rst.pop())
                }
            }

            "B" -> {
                if (lst.isNotEmpty()) {
                    lst.pop()
                }
            }

            else -> {
                lst.push(input[1][0])
            }
        }
    }
    println(lst.toCharArray() + rst.toCharArray().reversed())
}
