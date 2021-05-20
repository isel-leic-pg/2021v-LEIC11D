

/**
 * Represents an actor positioned in a two-dimensional space
 * and the direction in which he is facing or where he is moving.
 * @property position final grid position.
 * @property motion direction of movement or where it is facing.
 */
data class Actor(val position: Position, val motion: Direction?)