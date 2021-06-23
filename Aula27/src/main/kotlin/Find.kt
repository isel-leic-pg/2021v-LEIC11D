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

fun findSequencial(l:List<Double>, elem:Double) :Int? {
    for( idx in l.indices ) {
        if (elem == l[idx])
            return idx
        if (elem < l[idx]) return null
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
    if (l[mid] == elem) return mid
    return if (elem < l[mid])
        findBinary(l,elem,idxMin,mid-1)
    else
        findBinary(l,elem,mid+1,idxMax)
}

fun main() {
    val vals = createList(5.0,20.0,0.5)
    //val idx = findSequencial(vals,15.3)
    val idx = findBinary(vals,13.2)
    println(vals)
    println("idx = $idx")
}