
fun numberOfReadLines() :Int {
    var numberOfLines = 0
    while( readLine()!=null )
        numberOfLines++
    return numberOfLines
}

fun readLines() :List<String> {
    var lines = emptyList<String>()
    while (true) {
        val line = readLine() ?: return lines
        lines = lines + line
    }
    /*
    var line = readLine()
    while( line != null ) {
        lines += line
        line = readLine()
    }
    return lines
     */
}

fun main() {
    val lines = readLines()
    lines.forEach(::println)
    //println("Linhas = ${numberOfReadLines()}")
}

