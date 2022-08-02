import java.io.*
import java.util.*

// 괄호 -> 스택? ㅇㅇ 구하기 좋은듯

val scan = FastReader()
val sb = StringBuilder()

var N = 0

fun main() {
    input()
    pro()
}

fun input() {
    N = scan.nextInt()
}

fun pro() {
    var cnt = 1

    while(cnt <= N) {
//        val stack = mutableListOf<Char>()
//        val input = scan.nextLine()
//        for(e in input) {
//            stack.add(e)
//        }
//        while(stack.isNotEmpty()) {
//            val top = stack[stack.size-1]
//        }
        var flag = true
        val stack = Stack<Char>()
        val input = scan.nextLine()
//        println(input)
//        for (e in input) {
//            stack.push(e)
//        }
        //vps 가 아닌 케이스는 너무 많으니까 맞는거만 찾아주면 될것같은데
        //올림피아드 문제 참고해야할듯.. 그게 더 어려운거지만
        //음 스택에 무작정 쳐넣지 말고 넣을때 조건을 걸고 넣어주는 식으로
//        while (stack.isNotEmpty()) {
//            val top = stack.pop()
//            if(top == '(') {
//
//            }
//            // if (top == ')')
//            else {
//
//            }
//        }
        // indice : 인덱스
        for(i in input.indices) {
            if(input[i] == '(') {
                stack.push(input[i])
            } else {
                if(stack.isEmpty()) {
                    flag = false
                    break
                }
                stack.pop()
            }
        }

        if(stack.isEmpty() && flag) {
            println("YES")
        }
        else {
            println("NO")
        }
        cnt += 1
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