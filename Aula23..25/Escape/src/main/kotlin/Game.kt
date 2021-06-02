import pt.isel.canvas.KeyEvent

// Game Grid Features.
const val CELL_SIDE = 64    // Pixel dimension of each grid square.
const val GRID_WIDTH = 12   // Number of cells horizontally
const val GRID_HEIGHT = 10  // Number of cells vertically

const val ANIM_STEPS = 8    // Animation steps between cells. (sub-multiple of CELL_SIDE)

const val STATUS_BAR = CELL_SIDE    // Status bar height

/**
 * The three possible game states.
 */
enum class Status { RUN, WIN, LOSE }

/**
 * Game info.
 * @property hero Position and direction of hero
 * @property robots Position and direction of each robot
 * @property stepAnim step of animation 0..CELL_SIDE (0->final position; CELL_SIDE->start position)
 * @property garbage Position of each pile of junk
 * @property status Current state of the game
 */
data class Game(val hero:Actor, val robots:List<Actor>, val stepAnim:Int,
                val garbage:List<Position>, val status:Status)

/**
 * Checks if there is an actor in a position.
 */
private fun hasCollision(pos:Position, actors:List<Actor>) =
    actors.any { it.position == pos }

/**
 * Gets overlapping positions from a list of positions.
 */
fun List<Position>.overlapped() :List<Position> =
    filterIndexed { idx,pos ->  lastIndexOf(pos)>idx && indexOf(pos)==idx  }

/**
 * Performs one more game move when the player presses a key.
 */
fun Game.move(keyEvent: KeyEvent) :Game {
    if (status != Status.RUN) return this
    if (keyEvent.char == '*') return copy(hero = hero.jump(robots))
    val dir = directionOf(keyEvent.code) ?: return this
    val toPosition = hero.position + dir
    if ( hasCollision(toPosition, robots) || !toPosition.isValid(GRID_WIDTH,GRID_HEIGHT))  return this
    val afterRobots = robots.moveTo(toPosition)
    return Game( Actor(toPosition, dir), afterRobots , CELL_SIDE, garbage, status)
}

/**
 * Turns robot collisions into piles of garbage and removes robots that collide with each pile of garbage.
 * Determines the new game state.
 */
fun Game.collisions() :Game {
    val junks = robots.map { it.position }.overlapped() + garbage
    val robots = robots.filter { it.position !in junks }
    val newStatus = when {
        hero.position in garbage || hasCollision(hero.position,robots) -> Status.LOSE
        robots.isEmpty() -> Status.WIN
        else -> Status.RUN
    }
    return copy( robots = robots, garbage = junks, stepAnim = 0, status = newStatus)
}

/**
 * Performs an animation and if it is the last animation of a movement, detects collisions.
 */
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
    return Game( Actor(heroPos, null), createRobots(heroPos), stepAnim = 0, emptyList(), Status.RUN )
}

