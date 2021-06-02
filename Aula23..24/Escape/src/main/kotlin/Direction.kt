import pt.isel.canvas.DOWN_CODE
import pt.isel.canvas.LEFT_CODE
import pt.isel.canvas.RIGHT_CODE
import pt.isel.canvas.UP_CODE

/**
 * Diagonal key codes
 */
const val HOME_CODE = 36
const val END_CODE = 35
const val PG_UP_CODE = 33
const val PG_DOWN_CODE = 34

/**
 * Possible directions of the actors (Hero or Robot).
 *
 * Each direction has its respective displacements in two-dimensional space.
 * Position(0,0) corresponds to the upper left corner.
 * @property dx X-axis offset of the direction
 * @property dy Y-axis offset of the direction
 * @property key code of key associated with this direction
 */
enum class Direction(val dx:Int, val dy:Int, val key:Int) {
    LEFT(-1,0, LEFT_CODE), UP(0,-1, UP_CODE), RIGHT(1,0, RIGHT_CODE), DOWN(0,1, DOWN_CODE),
    LEFT_UP(-1,-1,HOME_CODE), RIGHT_UP(1,-1,PG_UP_CODE),
    LEFT_DOWN(-1,1,END_CODE), RIGHT_DOWN(1,1,PG_DOWN_CODE),
}

/**
 *  Returns the direction associated with the indicated key.
 *  @param key The key code
 *  @return direction associated with the key or null if there is no direction for the key
 */
fun directionOf( key:Int ) =
    Direction.values().firstOrNull{ it.key == key  }

/**
 * Access to the displacements of directions, including the absence of direction (null).
 */
fun Direction?.dx() = this?.dx ?: 0
fun Direction?.dy() = this?.dy ?: 0

fun sign(value :Int) = when {
    value == 0 -> 0
    value<0 -> -1
    else    -> +1
}

fun Motion.toDirection() :Direction {
    val dxu = sign(dx)
    val dyu = sign(dy)
    return Direction.values().first { it.dx==dxu && it.dy==dyu }
}