import pt.isel.canvas.DOWN_CODE
import pt.isel.canvas.LEFT_CODE
import pt.isel.canvas.RIGHT_CODE
import pt.isel.canvas.UP_CODE

const val HOME_CODE = 36
const val END_CODE = 35
const val PG_UP_CODE = 33
const val PG_DOWN_CODE = 34

enum class Direction(val dx:Int, val dy:Int) {
    LEFT(-1,0), UP(0,-1), RIGHT(1,0), DOWN(0,1),
    LEFT_UP(-1,-1), RIGHT_UP(1,-1),
    LEFT_DOWN(-1,1), RIGHT_DOWN(1,1),
}

fun Direction?.dx() = this?.dx ?: 0

fun Direction?.dy() = this?.dy ?: 0

fun directionOf( key:Int ) :Direction? = when (key) {
    LEFT_CODE -> Direction.LEFT
    RIGHT_CODE -> Direction.RIGHT
    UP_CODE -> Direction.UP
    DOWN_CODE -> Direction.DOWN
    HOME_CODE -> Direction.LEFT_UP
    END_CODE -> Direction.LEFT_DOWN
    PG_UP_CODE -> Direction.RIGHT_UP
    PG_DOWN_CODE -> Direction.RIGHT_DOWN
    else -> null
}