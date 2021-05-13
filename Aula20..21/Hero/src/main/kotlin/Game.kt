const val CELL_SIDE = 64
const val GRID_WIDTH = 6
const val GRID_HEIGHT = 5
const val ANIM_STEPS = 8  // Multiple of CELL_SIDE

const val SPRITE_DIV = 64
const val SPRITE_CELLS = 4

/**
 * @property hero
 * @property robots
 * @property stepAnim step of animation 0..CELL_SIDE (0->final position; CELL_SIDE->start position)
 */
data class Game(val hero: Actor, val robots:List<Actor>, val stepAnim:Int)

fun move(key :Int, game:Game) :Game {
    val dir = directionOf(key) ?: return game
    val toPosition = game.hero.position + dir
    return if (toPosition.isValid(GRID_WIDTH,GRID_HEIGHT))
        Game( Actor(toPosition, dir), game.robots, CELL_SIDE )
    else game
}

fun createGame() :Game {
    val heroPos = Position(GRID_WIDTH / 2, GRID_HEIGHT / 2)
    return Game( Actor(heroPos, null), createRobots(heroPos), 0)
}

val ALL_POSITIONS :List<Position>  = (0 until GRID_HEIGHT*GRID_WIDTH).map { Position( it%GRID_WIDTH, it/GRID_WIDTH ) }

fun createRobots(heroPos:Position) = (ALL_POSITIONS - heroPos).shuffled().take(10).map { Actor(it,null) }
