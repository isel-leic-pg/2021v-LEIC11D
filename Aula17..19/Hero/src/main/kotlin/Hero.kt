import pt.isel.canvas.*

const val CELL_SIDE = 64
const val GRID_WIDTH = 12
const val GRID_HEIGHT = 10
const val ANIM_STEPS = 8  // Multiple of CELL_SIDE

const val SPRITE_DIV = 64
const val SPRITE_CELLS = 4

/**
 * @property position final grid position
 * @property stepAnim step of animation 0..CELL_SIDE (0->final position; CELL_SIDE->start position)
 * @property motion direction of movement
 */
data class Hero(val position: Position, val stepAnim:Int, val motion:Direction?)

fun Canvas.drawArena(hero:Hero) {
    erase()
    drawGrid()
    drawHero(hero)
}

fun Canvas.drawHero(hero: Hero) {
    val x = hero.position.x * CELL_SIDE - hero.stepAnim * hero.motion.dx()
    val y = hero.position.y * CELL_SIDE - hero.stepAnim * hero.motion.dy()
    val yImg = SPRITE_DIV * when(hero.motion) {
        Direction.LEFT -> 1
        Direction.RIGHT -> 2
        Direction.UP -> 3
        Direction.DOWN, null -> 0
    }
    println("${hero.stepAnim} ${(hero.stepAnim / (CELL_SIDE / ANIM_STEPS))}")
    val xImg = SPRITE_DIV * ((hero.stepAnim / (CELL_SIDE / ANIM_STEPS)) % SPRITE_CELLS)
    drawImage("Hero.png|$xImg,$yImg,$SPRITE_DIV,$SPRITE_DIV", x, y, CELL_SIDE, CELL_SIDE)
}

fun move(key :Int, hero:Hero) :Hero {
    val dir = directionOf(key) ?: return hero
    val toPosition = hero.position + dir
    return if (toPosition.isValid(GRID_WIDTH,GRID_HEIGHT)) Hero(toPosition,CELL_SIDE,dir) else hero
}

fun Canvas.drawGrid() {
    (CELL_SIDE..height step CELL_SIDE).forEach {
        drawLine(0, it, width, it, WHITE, 1) // horizontal
    }
    (CELL_SIDE..width step CELL_SIDE).forEach {
        drawLine(it, 0, it, height, WHITE, 1) // vertical
    }
}

fun main() {
    onStart {
        val cv = Canvas(CELL_SIDE*GRID_WIDTH, CELL_SIDE*GRID_HEIGHT, BLACK)
        var hero = Hero( Position(GRID_WIDTH/2, GRID_HEIGHT/2), stepAnim = 0, null)
        cv.drawArena(hero)
        cv.onKeyPressed { ke :KeyEvent ->
            hero = move(ke.code,hero)
            cv.drawArena(hero)
        }
        cv.onTimeProgress(50) {
            if (hero.stepAnim>0) { // está em movimento
                hero = hero.copy( stepAnim = hero.stepAnim - CELL_SIDE/ANIM_STEPS)
                cv.drawArena(hero)
            }
        }
    }
    onFinish {  }
}
