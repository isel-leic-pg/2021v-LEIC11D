import pt.isel.canvas.KeyEvent

const val CELL_SIDE = 64
const val GRID_WIDTH = 12
const val GRID_HEIGHT = 10
const val ANIM_STEPS = 8  // Multiple of CELL_SIDE

const val SPRITE_DIV = 64
const val SPRITE_CELLS = 4

/**
 * @property hero
 * @property robots
 * @property stepAnim step of animation 0..CELL_SIDE (0->final position; CELL_SIDE->start position)
 */
data class Game(val hero: Actor, val robots:List<Actor>, val stepAnim:Int, val garbage:List<Position>)

/**
 * Checks if there is an actor in a position.
 */
private fun hasCollision(pos:Position, actors:List<Actor>) =
    actors.any { it.position == pos }

fun List<Position>.overlapped() :List<Position> =
    filterIndexed { idx,pos ->  lastIndexOf(pos)>idx && indexOf(pos)==idx  }
/**
 * Performs one more game move when the player presses a key.
 */
fun Game.move(keyEvent: KeyEvent) :Game {
    if (keyEvent.char == '*') return copy(hero = hero.jump(robots))
    val dir = directionOf(keyEvent.code) ?: return this
    val toPosition = hero.position + dir
    if ( hasCollision(toPosition, robots) || !toPosition.isValid(GRID_WIDTH,GRID_HEIGHT))  return this
    val afterRobots = robots.moveTo(toPosition)
    return Game( Actor(toPosition, dir), afterRobots , CELL_SIDE, garbage)
}

fun Game.collisions() :Game {
    val junks = robots.map { it.position }.overlapped() + garbage
    val robots = robots.filter { it.position !in junks }
    return copy( robots = robots, garbage = junks, stepAnim = 0)
}

fun Game.step() :Game {
    if (this.stepAnim == 0) return this
    val anim = this.stepAnim - CELL_SIDE / ANIM_STEPS
    return if (anim==0) collisions() else copy(stepAnim=anim)
}

/**
 * Creates the initial game
 */
fun createGame() :Game {
    val heroPos = Position(GRID_WIDTH / 2, GRID_HEIGHT / 2)
    return Game( Actor(heroPos, null), createRobots(heroPos), stepAnim = 0, emptyList() )
}

