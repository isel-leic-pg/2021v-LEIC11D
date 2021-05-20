/**
 * Represents a position in a two-dimensional space.
 * @property x value from 0 to the maximum width of the space
 * @property y value from 0 to the maximum height of the space
 */
data class Position(val x:Int, val y:Int)

/**
 * Sums the displacement from one direction to a position, returning to the new position.
 * @receiver original position.
 * @param dir direction to add.
 * @return resulting direction.
 */
operator fun Position.plus( dir:Direction ) =
    Position( x + dir.dx(), y + dir.dy() )

/**
 * Checks whether the position is within the limits.
 * @param xSize Space width.
 * @param ySize Space height.
 */
fun Position.isValid(xSize:Int, ySize:Int) =
    x in 0 until xSize && y in 0 until ySize


