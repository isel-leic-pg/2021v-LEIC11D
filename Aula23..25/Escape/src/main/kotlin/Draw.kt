import pt.isel.canvas.Canvas
import pt.isel.canvas.WHITE

const val SPRITE_DIV = 64   // Size of the squares in the image file.
const val SPRITE_CELLS = 4  // Number of images for each animation.

/**
 * Draws the entire arena of the game: Grid, Hero, Robots, Junks and Status bar
 * @receiver where it will be drawn
 * @param game All game information
 */
fun Canvas.drawArena(game: Game) {
    erase()
    drawGrid()
    drawActor(game.hero, game.stepAnim,"Hero.png")
    game.robots.forEach {
        drawActor( it , game.stepAnim, "robot.png")
    }
    game.garbage.forEach { pos ->
        drawJunk(pos)
    }
    drawStatus(game)
}

const val TEXT_BASE = 10 // Vertical separation of text from base

/**
 * Displays game information in status bar.
 */
private fun Canvas.drawStatus(g:Game) {
    drawRect(0,height-STATUS_BAR,width,STATUS_BAR,0x555555)
    drawText(CELL_SIDE, height-TEXT_BASE, "Robots:${g.robots.size}", WHITE)
    if (g.status!=Status.RUN)
        drawText(
            width/2+CELL_SIDE, height-TEXT_BASE,
            "You ${if (g.status==Status.WIN) "win" else "lose"}",
            WHITE
        )
}
/**
 * Draw an actor.
 * @receiver where it will be drawn
 * @param actor Actor to be drawn
 * @param stepAnim Animation step between two grid cells (0->final position)
 * @param pngName File with images of the actor
 */
private fun Canvas.drawActor(actor: Actor, stepAnim: Int, pngName:String) {
    val x = actor.position.x * CELL_SIDE - stepAnim * actor.motion.dx()
    val y = actor.position.y * CELL_SIDE - stepAnim * actor.motion.dy()
    val yImg = SPRITE_DIV * when(actor.motion) {
        Direction.LEFT, Direction.LEFT_UP, Direction.LEFT_DOWN -> 1
        Direction.RIGHT, Direction.RIGHT_UP, Direction.RIGHT_DOWN -> 2
        Direction.UP -> 3
        Direction.DOWN, null -> 0
    }
    val xImg = SPRITE_DIV * ((stepAnim / (CELL_SIDE / ANIM_STEPS)) % SPRITE_CELLS)
    drawImage("$pngName|$xImg,$yImg,$SPRITE_DIV,$SPRITE_DIV", x, y, CELL_SIDE, CELL_SIDE)
}

/**
 * Draw the background grid.
 * @receiver where it will be drawn
 */
private fun Canvas.drawGrid() {
    (CELL_SIDE..height step CELL_SIDE).forEach {
        drawLine(0, it, width, it, WHITE, 1) // horizontal
    }
    (CELL_SIDE..width step CELL_SIDE).forEach {
        drawLine(it, 0, it, height, WHITE, 1) // vertical
    }
}

/**
 * Draws the garbage in a cell.
 */
private fun Canvas.drawJunk(junk:Position) {
    drawImage("junk", junk.x*CELL_SIDE, junk.y*CELL_SIDE, CELL_SIDE, CELL_SIDE)
}