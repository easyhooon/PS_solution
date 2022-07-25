import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    while (true) {
        val str = br.readLine() ?: break
        var a = 0
        var b = 0

        var c = 0
        var d = 0

        for (i in str) {
            when(i) {
                in 'a'..'z' -> a++
                in 'A'..'Z' -> b++
                in '0'..'9' -> c++
                ' ' -> d++
            }
        }
        bw.write("$a $b $c $d\n")
    }
    bw.flush()
    bw.close()
    br.close()
}