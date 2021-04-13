import pt.isel.canvas.*

fun start() {
    println("Start")
    val cv = Canvas(600,300, CYAN)
    cv.drawCircle(50,50,45,RED,10)
    cv.drawCircle(550,250,50, GREEN)
    cv.onMouseDown { me : MouseEvent ->
        println(me)
        cv.drawCircle(me.x,me.y,10, BLACK)
    }
}

fun main() {
    println("Begin")
    onStart(::start)
    onFinish {
        println("Bye")
    }
    println("End")
}