import java.io.BufferedReader
import java.io.InputStreamReader

val case = "UCPC"
val check = BooleanArray(4)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val str = br.readLine()

    var index = 0
    for (i in str.indices) {
        if (str[i] == case[index]) {
//            println("$i $index")
//            println("${str[i]} == ${case[index]}")
            check[index] = true
            index++

            if (index >= 4) break
        }
    }


    if (check.contains(false)) {
        println("I hate UCPC")
    } else {
        println("I love UCPC")
    }
}
