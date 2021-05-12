import pt.isel.canvas.*

const val CELL_SIDE = 64
const val GRID_WIDTH = 12
const val GRID_HEIGHT = 10
const val ANIM_STEPS = 8  // Multiple of CELL_SIDE

const val SPRITE_DIV = 64
const val SPRITE_CELLS = 4

data class Game(val hero: Actor, val robots:List<Actor>, val stepAnim:Int)

/**
 * @property position final grid position
 * @property stepAnim step of animation 0..CELL_SIDE (0->final position; CELL_SIDE->start position)
 * @property motion direction of movement
 */
data class Actor(val position: Position, val motion:Direction?)

fun Canvas.drawArena(actor:Actor) {
    erase()
    drawGrid()
    drawHero(actor)
}

fun Canvas.drawHero(actor: Actor) {
    val x = actor.position.x * CELL_SIDE - actor.stepAnim * actor.motion.dx()
    val y = actor.position.y * CELL_SIDE - actor.stepAnim * actor.motion.dy()
    val yImg = SPRITE_DIV * when(actor.motion) {
        Direction.LEFT, Direction.LEFT_UP, Direction.LEFT_DOWN -> 1
        Direction.RIGHT, Direction.RIGHT_UP, Direction.RIGHT_DOWN -> 2
        Direction.UP -> 3
        Direction.DOWN, null -> 0
    }
    val xImg = SPRITE_DIV * ((actor.stepAnim / (CELL_SIDE / ANIM_STEPS)) % SPRITE_CELLS)
    drawImage("Hero.png|$xImg,$yImg,$SPRITE_DIV,$SPRITE_DIV", x, y, CELL_SIDE, CELL_SIDE)
}

fun move(key :Int, actor:Actor) :Actor {
    val dir = directionOf(key) ?: return actor
    val toPosition = actor.position + dir
    return if (toPosition.isValid(GRID_WIDTH,GRID_HEIGHT)) Actor(toPosition,CELL_SIDE,dir) else actor
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
        var game = Game( Actor(Position(GRID_WIDTH/2, GRID_HEIGHT/2),null), emptyList(), 0)
        cv.drawArena(game)
        cv.onKeyPressed { ke :KeyEvent ->
            game = move(ke.code,game)
            cv.drawArena(game)
        }
        cv.onTimeProgress(50) {
            if (game.stepAnim>0) { // está em movimento
                game = game.copy( stepAnim = game.stepAnim - CELL_SIDE/ANIM_STEPS)
                cv.drawArena(game)
            }
        }
    }
    onFinish {  }
}
