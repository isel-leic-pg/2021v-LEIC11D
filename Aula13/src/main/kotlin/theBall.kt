import pt.isel.canvas.*

const val RADIUS = 50

private fun Motion.toColor() = when {
    dx >= 0 && dy >= 0 -> RED
    dx >= 0 && dy <= 0 -> GREEN
    dx <= 0 && dy >= 0 -> BLUE
    else -> BLACK
}

private fun Canvas.drawBall(b: Ball) {
    erase()
    val color = b.motion.toColor()
    drawCircle(b.position.x,b.position.y,b.radius, color)
}

fun main() {
    onStart {
        val cv = Canvas(600,400)
        var ball = Ball( Position(100,100), randomMotion() ,RADIUS)
        cv.drawBall(ball)
        cv.onTimeProgress(20) {
            ball = ball.move(cv.width,cv.height)
            cv.drawBall(ball)
        }
        cv.onKeyPressed { ball = ball.processKey(it.char) }
        cv.onMouseDown { ball = ball.copy(position= Position(it.x,it.y)) }
    }
    onFinish {  }
}