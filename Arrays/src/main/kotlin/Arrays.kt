import pt.isel.canvas.*

fun main(args: Array<String>) {
    println("Hello PG")
    args.forEach(::println)
    onStart {
        val cv = Canvas(300,400, BLACK)
        cv.drawCircle(150,200,100, YELLOW,3)
        args.forEachIndexed { idx, txt ->
            cv.drawText(50,50*idx +50 ,txt, WHITE)
        }
    }
    onFinish{  }
}

/*
fun main() {
    //val l :List<Int> = listOf(27,32,40,12)
    //val l :MutableList<Int> = mutableListOf(27,32,40,12)
    val l :Array<Int> = arrayOf(27,32,40,12)
    l.forEachIndexed{ idx, elem ->
        println("[$idx] -> $elem")
    }
    l[ 3 ] = 54
    //l.add(16)
    println("------------")
    for (idx in l.indices)
        println("[$idx] -> ${l[idx]}")
}
 */