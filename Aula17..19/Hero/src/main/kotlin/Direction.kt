import pt.isel.canvas.DOWN_CODE
import pt.isel.canvas.LEFT_CODE
import pt.isel.canvas.RIGHT_CODE
import pt.isel.canvas.UP_CODE

enum class Direction(val dx:Int, val dy:Int) { LEFT(-1,0), UP(0,-1), RIGHT(1,0), DOWN(0,1) }

fun Direction?.dx() = this?.dx ?: 0

fun Direction?.dy() = this?.dy ?: 0
/*
when (this) {
    Direction.UP -> -1
    Direction.DOWN -> +1
    else -> 0
}
*/

fun directionOf( key:Int ) :Direction? = when (key) {
    LEFT_CODE -> Direction.LEFT
    RIGHT_CODE -> Direction.RIGHT
    UP_CODE -> Direction.UP
    DOWN_CODE -> Direction.DOWN
    else -> null
}