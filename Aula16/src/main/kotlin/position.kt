import kotlin.math.sqrt

val VELOCITY_LIMITS = -10..10

data class Position(val x:Int, val y:Int)

data class Motion(val dx:Int, val dy:Int)

//fun plus( p:Position, m:Motion) = Position(p.x+m.dx, p.y+m.dy)
operator fun Position.plus(m:Motion) = Position(x + m.dx, y + m.dy)


fun distance(a :Position, b :Position) = sqrt( (square(a.x-b.x) + square(a.y-b.y)).toFloat() )

fun square( n :Int ) = n * n

fun randomMotion() = Motion( VELOCITY_LIMITS.random() , VELOCITY_LIMITS.random() )

