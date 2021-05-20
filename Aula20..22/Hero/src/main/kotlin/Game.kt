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

/**
 * Checks if there is an actor in a position.
 */
fun hasCollision(pos:Position, actors:List<Actor>) = actors.any { it.position == pos }

/**
 * Performs one more game move when the player presses a key.
 */
fun Game.move(key: Int) :Game {     //  : Game.(Int)->Game
    val dir = directionOf(key) ?: return this
    val toPosition = hero.position + dir
    if ( hasCollision(toPosition, robots) ) return this
    //if ( robots.any{ it.position==toPosition }) return this
    return if (toPosition.isValid(GRID_WIDTH,GRID_HEIGHT))
        Game( Actor(toPosition, dir), robots, CELL_SIDE )
    else this
}

/**
 * Creates the initial game
 */
fun createGame() :Game {
    val heroPos = Position(GRID_WIDTH / 2, GRID_HEIGHT / 2)
    return Game( Actor(heroPos, null), createRobots(heroPos), stepAnim = 0)
}

/**
 * All positions in the game arena.
 */
val ALL_POSITIONS :List<Position>  = (0 until GRID_HEIGHT*GRID_WIDTH).map { Position( it%GRID_WIDTH, it/GRID_WIDTH ) }

/**
 * Create the initial list of 10 robots in random positions avoiding the hero's position.
 */
fun createRobots(heroPos:Position) = (ALL_POSITIONS - heroPos).shuffled().take(10).map { Actor(it,null) }
