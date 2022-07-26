import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

var n = 0
var br = BufferedReader(InputStreamReader(System.`in`))
var sb = StringBuilder()
var st = ""
var size = 0
lateinit var a : IntArray

fun main() {
    input()
    pro()
}

fun input() {
    st = br.readLine()
    a = IntArray(st.length)
    size = st.length / 2


    for(i in st.indices) {
        a[i] = Character.getNumericValue(st[i])
    }
    //println(a.joinToString())
}

fun pro() {
    val first =  IntArray(size)
    val second = IntArray(size)

    for(i in 0 until size) {
        first[i] = a[i]
    }

    for(i in size until st.length) {
        second[i-size] = a[i]
    }

//    println(first.joinToString())
//    println(second.joinToString())

    if(first.sum() == second.sum()) {
        println("LUCKY")
    } else {
        println("READY")
    }
}