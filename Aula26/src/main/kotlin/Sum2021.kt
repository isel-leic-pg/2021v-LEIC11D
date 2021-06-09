data class ResultFind(val n1:Int, val n2:Int)

fun findTwoNumbersSum2021(ns:List<Int>) :ResultFind? {
    for(idx1 in 0 .. ns.size-2)
        for(idx2 in idx1+1 .. ns.size-1)
            if ( ns[idx1]+ns[idx2] == 2021)
                return ResultFind(ns[idx1],ns[idx2])
    return null
}

fun main() {
    val numbers = readLines().map { it.toInt() }
       findTwoNumbersSum2021(numbers)

    /*
    var done = false
    var idx1 =0
    var idx2 =0
    while( idx1 <= numbers.size-2 && !done ) {
        val n1 = numbers[idx1]
        idx2 = idx1+1
        idx1++
        while( idx2 < numbers.size && !done) {
            val n2 = numbers[idx2]
            idx2++
            if ( n1+n2 == 2021)
                done = true
        }
    }
    println("${numbers[idx1-1]} + ${numbers[idx2-1]} == 2021")
    */
}