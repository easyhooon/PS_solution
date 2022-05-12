package doing

import java.io.*
import java.util.*

internal val sb = StringBuilder()
internal val bw = BufferedWriter(OutputStreamWriter(System.out))

internal var L: Int = 0
internal var C: Int = 0

lateinit var selected: IntArray
lateinit var alphabets: CharArray

fun main() {
    val scan = FastReader()

    L = scan.nextInt()
    C = scan.nextInt()

    selected = IntArray(L + 1)
    alphabets = CharArray(C + 1)

    val list = scan.nextLine().split(" ")

    for (i in 1..C) {
        //charAt[0] 과 호응
        alphabets[i] = list[i - 1][0]
    }

    Arrays.sort(alphabets)

//    repeat(C) { i ->
//        println(alphabets[i + 1])
//    }

    recursiveFunc(1)

    println(sb.toString())
//    bw.flush()
//    bw.close()
}

fun isVowel(x: Char): Boolean {
    return x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u'
}

// 중복 없음
// 알파벳 증가하는 순(순서 존재), 최소 한개의 모음, 최소 두개의 자음
// * 굳이 알파벳을 숫자로 변환하지않아도 된다!! 모음은 몇개 안되니까!!
// 만약 M 개를 전부 고름 => 조건에 맞는 탐색을 한 가지 성공한 것
// 아직 M 개를 고르지 않음  => k 번째부터 M번째 원소를 조건에 맞게 고르는 방법을 시도
fun recursiveFunc(k: Int) {
    if (k == L + 1) {
        // 다 골랐다!
        var vowel = 0
        var consonant = 0

        for (i in 1..L) {
            if (isVowel(alphabets[selected[i]])) vowel++
            else consonant++
        }

        if(vowel >= 1 && consonant >= 2) {
            for (i in 1..L) {
                //bw.write(alphabets[selected[i]])
                sb.append(alphabets[selected[i]])
            }
            //bw.write("\n")
            sb.append("\n")
        }
        
    } else {
        // k+1 번째 부터 L까지 결정
        for (cand in selected[k-1]+1..C) {
            selected[k] = cand

            recursiveFunc(k + 1)

            selected[k] = 0
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