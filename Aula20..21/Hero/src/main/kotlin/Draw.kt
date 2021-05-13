import pt.isel.canvas.Canvas
import pt.isel.canvas.WHITE

fun Canvas.drawArena(game: Game) {
    erase()
    drawGrid()
    drawActor(game.hero, game.stepAnim,"Hero.png")
    game.robots.forEach {
        drawActor( it , game.stepAnim, "robot.png")
    }
}

fun Canvas.drawActor(actor: Actor, stepAnim: Int, pngName:String) {
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

fun Canvas.drawGrid() {
    (CELL_SIDE..height step CELL_SIDE).forEach {
        drawLine(0, it, width, it, WHITE, 1) // horizontal
    }
    (CELL_SIDE..width step CELL_SIDE).forEach {
        drawLine(it, 0, it, height, WHITE, 1) // vertical
    }
}