
fun main() {
    fx()
}

fun fx() {
    print("Linha? ")
    val maybeLine = readLine()
    val len :Int? = maybeLine?.length
    println(len)
    val str :String? = maybeLine?.trim()
    println(str)
    val ln :Int = len ?: 0
    println(ln)
    val x = maybeLine?.length ?: 0
    println(x)

    //val l:String = maybeLine!!
    /*
    if (maybeLine!=null)
        println(maybeLine.length)
    else
        println("linha não existe.")

     */
}