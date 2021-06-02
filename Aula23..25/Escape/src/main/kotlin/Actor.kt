

/**
 * Represents an actor positioned in a two-dimensional space
 * and the direction in which he is facing or where he is moving.
 * @property position final grid position.
 * @property motion direction of movement or where it is facing.
 */
data class Actor(val position: Position, val motion: Direction?)


/**
 * All positions in the game arena.
 */
private val ALL_POSITIONS :List<Position>  = (0 until GRID_HEIGHT*GRID_WIDTH).map { Position( it%GRID_WIDTH, it/GRID_WIDTH ) }

/**
 * Create the initial list of 10 robots in random positions avoiding the hero's position.
 */
fun createRobots(heroPos:Position) :List<Actor> =
    (ALL_POSITIONS - heroPos).shuffled().take(10).map { Actor(it,null) }


fun Actor.jump( others:List<Actor> ) = Actor(
    (ALL_POSITIONS - others.map { it.position } - position ).random(),
    motion = null
)

fun List<Actor>.moveTo(target:Position ) :List<Actor> =
    map{ actor ->
        val dir = (target - actor.position).toDirection()
        Actor( actor.position + dir , dir )
    }