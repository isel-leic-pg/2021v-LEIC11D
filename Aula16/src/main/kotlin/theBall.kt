import pt.isel.canvas.*

const val RADIUS = 50

private fun Motion.toColor() = when {
    dx >= 0 && dy >= 0 -> RED
    dx >= 0 && dy <= 0 -> GREEN
    dx <= 0 && dy >= 0 -> BLUE
    else -> BLACK
}

private fun Canvas.drawBall(b: Ball?) {
    erase()
    if (b!=null) {
        val color = b.motion.toColor()
        drawCircle(b.position.x, b.position.y, b.radius, color)
    }
}

fun main() {
    onStart {
        val cv = Canvas(300,600)
        var balls = listOf<Ball>()
        cv.drawBall(ball)
        cv.onTimeProgress(20) {
            balls = balls.map { it.move(cv.width,cv.height) }
            //TODO: convert function drawBall() in drawBalls()
            cv.drawBall(ball)
        }
        cv.onKeyPressed {
            if (it.char=='b')
                balls = balls + Ball(Position(0,0),randomMotion(),RADIUS)
        }
        //cv.onMouseDown { ball = ball?.copy(position= Position(it.x,it.y)) }
    }
    onFinish {  }
}