val VELOCITY_LIMITS = -10..10

data class Position(val x:Int, val y:Int)

data class Motion(val dx:Int, val dy:Int)

//fun plus( p:Position, m:Motion) = Position(p.x+m.dx, p.y+m.dy)
operator fun Position.plus(m:Motion) = Position(x + m.dx, y + m.dy)

fun randomMotion() = Motion( VELOCITY_LIMITS.random() , VELOCITY_LIMITS.random() )

