import java.time.LocalDate
import java.time.ZoneId


fun main(args: Array<String>) {
    val zoneId = ZoneId.of("Asia/Seoul")
    val date = LocalDate.now(zoneId)
    println(date)
}
