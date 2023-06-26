import java.util.regex.Pattern

fun main() {
    val regex = Pattern.compile("(100+1+|01)+")
    val input = readln()
    val matcher = regex.matcher(input)

    if(matcher.matches()) {
        println("SUBMARINE")
    } else {
        println("NOISE")
    }
}