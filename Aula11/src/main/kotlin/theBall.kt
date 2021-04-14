import pt.isel.canvas.*

const val RADIUS = 50
const val VELOCITY = 4
const val DELTA_RADIUS = 1

data class BallState(val x:Int, val y:Int, val dx:Int, val dy:Int, val radius:Int)

fun moveBall(b :BallState, canvas: Canvas) :BallState {
    val x  = b.x + b.dx
    val dx = if (x >= canvas.width-b.radius || x<=b.radius) -b.dx else b.dx
    val y = b.y + b.dy
    val dy = if (y >= canvas.height-b.radius || y<=b.radius) -b.dy else b.dy
    return BallState(x,y,dx,dy,b.radius)
}

fun drawBall(b :BallState, canvas: Canvas) {
    canvas.erase()
    canvas.drawCircle(b.x,b.y,b.radius, RED)
}

fun main() {
    onStart {
        val cv = Canvas(600,400)
        var bs = BallState(100,100,VELOCITY,VELOCITY,RADIUS)
        drawBall(bs,cv)
        cv.onTimeProgress(20) {
            bs = moveBall(bs,cv)
            drawBall(bs,cv)
        }
        cv.onKeyPressed { ke:KeyEvent ->
           if (ke.char=='r') {
               bs = BallState(bs.x,bs.y,bs.dx,bs.dy, bs.radius-DELTA_RADIUS)
               drawBall(bs,cv)
           }
        }
    }
    onFinish {  }
}