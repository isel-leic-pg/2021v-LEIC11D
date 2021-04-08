
data class Time(val hours:Int, val minutes:Int, val secs:Int)

fun toTime(secs :Int) = Time(secs / 3600, secs % 3600 / 60, secs % 60)

//fun toText(tm:Time) = "${tm.hours}:${tm.minutes}:${tm.secs}"

fun Time.toText() = "$hours:$minutes:$secs"

//fun Int.add10() = this+10

fun toText(secs:Int, tm:Time) = "$secs = ${tm.toText()}"

fun main() {
    val secs = readInt("Total de segundos")
    val  tm = toTime(secs)
//    println("$secs = ${toText(tm)}")
    println("$secs = ${tm.toText()}")
    println(toText(secs,tm))
//    println(23.add10())
}