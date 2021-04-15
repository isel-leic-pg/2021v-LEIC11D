import pt.isel.canvas.*

const val RADIUS = 50
const val VELOCITY = 4
const val DELTA_RADIUS = 1
val RADIUS_LIMITS = 10..150
val VELOCITY_LIMITS = -10..10

data class Position(val x:Int, val y:Int)
fun plus( p:Position, m:Motion) = Position(p.x+m.dx, p.y+m.dy)

data class Motion(val dx:Int, val dy:Int)

data class Ball(val position:Position, val motion:Motion, val radius:Int)

fun moveBall(b :Ball, limitRight:Int, limitDown:Int) :Ball {
    val pos = plus(b.position,b.motion)
    val dx = if ( b.motion.dx>0 && pos.x >= limitRight-b.radius ||
                  b.motion.dx<0 && pos.x<=b.radius) -b.motion.dx
             else b.motion.dx
    val dy = if ( b.motion.dy>0 && pos.y >= limitDown-b.radius ||
                  b.motion.dy<0 && pos.y<=b.radius) -b.motion.dy
             else b.motion.dy
    return b.copy(position = pos, motion = Motion(dx,dy))   //Ball( pos, Motion(dx,dy) , b.radius)
}

fun drawBall(b :Ball, canvas: Canvas) {
    canvas.erase()
    canvas.drawCircle(b.position.x,b.position.y,b.radius, RED)
}

fun prcessKey(b :Ball, key :Char) = b.copy(
       motion = if (key=='m') randomMotion() else b.motion,
       radius = when {
           key=='r' &&  b.radius >= RADIUS_LIMITS.first -> b.radius-DELTA_RADIUS
           key=='R' &&  b.radius <= RADIUS_LIMITS.last -> b.radius+DELTA_RADIUS
           else -> b.radius
       }
   )

fun randomMotion() = Motion( VELOCITY_LIMITS.random() , VELOCITY_LIMITS.random() )

fun main() {
    onStart {
        val cv = Canvas(600,400)
        var bs = Ball( Position(100,100), randomMotion() ,RADIUS)
        drawBall(bs,cv)
        cv.onTimeProgress(20) {
            bs = moveBall(bs,cv.width,cv.height)
            drawBall(bs,cv)
        }
        cv.onKeyPressed { bs = prcessKey(bs,it.char) }
        cv.onMouseDown { bs = bs.copy(position= Position(it.x,it.y)) }  //Ball( Position(me.x,me.y) , bs.motion, bs.radius ) }
    }
    onFinish {  }
}