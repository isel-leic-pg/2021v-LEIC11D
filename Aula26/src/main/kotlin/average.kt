

fun main() {
    println( "Média = ${readLines()
        .map { it.toInt() }
        .average()}")
    /*
    val lines :List<String> = readLines()
    println(lines)
    val numbers = lines.map { it.toInt() }
    println( numbers )
    var sum = 0
    numbers.forEach { sum+=it }
    val avg = sum.toFloat() / numbers.size
    println("Média=$avg")
     */
}