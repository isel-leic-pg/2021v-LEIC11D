import kotlin.random.Random


fun createRandomList(min:Double, max:Double, size:Int) :List<Double> {
    return List(size) { Random.nextDouble() * (max-min) + min }
    /*
    val lst = mutableListOf<Double>()
    repeat(size) {
        lst.add( Random.nextDouble() * (max-min) + min )
    }
    return lst
     */
}

fun createList(min:Double, max:Double, delta:Double) = List(((max-min)/delta).toInt()+1) { it * delta + min }

// 0!  ->  1
// n!  ->  n * (n-1)!
// n! -> n * (n-1) * (n-2) ... 3 * 2  * 1

fun fact(n:Int) :Long =
    if (n==0) 1
    else n * fact(n-1)

fun factI(n:Int) :Long {
    var f = 1L
    for ( i in 2..n )
        f *= i
    return f
}

// List<T> ->  emptyList ||  T , List<T>

fun findSequentialR(l:List<Double>, elem:Double, idxMin:Int=0) :Int? = when {
    idxMin>=l.size -> null
    l[idxMin]==elem -> idxMin
    else -> findSequentialR( l, elem, idxMin+1)
}

fun findSequential(l:List<Double>, elem:Double) :Int? {
    for( idx in l.indices ) {
        if (elem == l[idx])
            return idx
        //if (elem < l[idx]) return null
    }
    return null
    /*
    var idx = 0
    while( idx < l.size ) {
        if (elem == l[idx])
            return idx
        idx++
    }
    return null
     */
}

fun findBinary(l:List<Double>, elem: Double, idxMin:Int=0, idxMax:Int=l.size-1) :Int? {
    if (idxMin > idxMax)
        return null
    val mid = (idxMax+idxMin)/2
    if (l[mid] == elem)
        return mid
    return if (elem < l[mid])
        findBinary(l,elem,idxMin,mid-1)
    else
        findBinary(l,elem,mid+1,idxMax)
}

fun findBinaryI(l:List<Double>, elem: Double) :Int? {
    var idxMin = 0
    var idxMax = l.size-1
    while (idxMin <= idxMax) {
        val mid = (idxMax+idxMin)/2
        if (l[mid] == elem) return mid
        if (elem < l[mid]) idxMax = mid-1
        else idxMin = mid+1
    }
    return null
}

fun main() {
    println( fact(20) )
    val vals = createList(5.0,20.0,0.5)
    //val idx = findSequencialR(vals,15.3)
    val idx = findBinaryI(vals,13.5)
    println(vals)
    println("idx = $idx")
}