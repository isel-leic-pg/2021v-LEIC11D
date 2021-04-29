const val DELTA_RADIUS = 1
val RADIUS_LIMITS = 10..150

data class Ball(val position:Position, val motion:Motion, val radius:Int)

fun Ball.move(limitRight:Int, limitDown:Int) :Ball {
    fun reverseIf(value:Int, pos:Int, limit:Int) :Int =
        if ( value>0 && pos >= limit-radius || value<0 && pos<=radius) -value else value

    val pos = position + motion
    val dx = reverseIf(motion.dx, pos.x, limitRight)
    val dy = reverseIf(motion.dy, pos.y, limitDown)
    return copy(position = pos, motion = Motion(dx,dy))
}

fun Ball.processKey(key: Char) = copy(
    motion = if (key=='m') randomMotion() else motion,
    radius = when {
        key=='r' &&  radius > RADIUS_LIMITS.first -> radius -DELTA_RADIUS
        key=='R' &&  radius < RADIUS_LIMITS.last -> radius +DELTA_RADIUS
        else -> radius
    }
)
