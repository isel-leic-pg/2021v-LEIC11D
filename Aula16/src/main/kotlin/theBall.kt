import pt.isel.canvas.*

const val RADIUS = 50

private fun Motion.toColor() = when {
    dx >= 0 && dy >= 0 -> RED
    dx >= 0 && dy <= 0 -> GREEN
    dx <= 0 && dy >= 0 -> BLUE
    else -> BLACK
}

private fun Canvas.drawBalls(bs: List<Ball>) {
    erase()
    bs.forEach { b ->
        val color = b.motion.toColor()
        drawCircle(b.position.x, b.position.y, b.radius, color)
    }
}

fun main() {
    onStart {
        val cv = Canvas(300,600)
        var balls = listOf<Ball>()
        cv.drawBalls(balls)
        cv.onTimeProgress(20) {
            balls = balls.map { it.move(cv.width,cv.height) }
            cv.drawBalls(balls)
        }
        cv.onKeyPressed {
            if (it.char=='b') {
                balls = balls + Ball(Position(0, 0), randomMotion(), RADIUS)
            }
        }
        cv.onMouseDown {
            val mp = Position(it.x,it.y)
            val clicked = balls.filter{ b -> distance(b.position,mp) <= b.radius }
            balls = balls - clicked
        }
    }
    onFinish {  }
}