

data class Position(val x:Int, val y:Int)

operator fun Position.plus( dir:Direction ) = Position( x + dir.dx(), y + dir.dy() )

fun Position.isValid(xSize:Int, ySize:Int) = x in 0 until xSize && y in 0 until ySize


