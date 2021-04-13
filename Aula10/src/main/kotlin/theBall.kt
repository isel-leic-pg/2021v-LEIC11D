import pt.isel.canvas.*

fun main() {
    onStart {
        val cv = Canvas(600,200)
        var x = 100
        cv.drawCircle(x,100,50,RED)
        cv.onTimeProgress(20) {
            x = x+4
            cv.erase()
            cv.drawCircle(x,100,50,RED)
        }
    }
    onFinish {  }
}