import pt.isel.canvas.*

/**
 * Main function of the game.
 */
fun main() {
    onStart {
        val cv = Canvas(CELL_SIDE * GRID_WIDTH, CELL_SIDE * GRID_HEIGHT + STATUS_BAR, BLACK)
        var game = createGame()
        cv.drawArena(game)
        cv.onKeyPressed { ke: KeyEvent ->
            game = game.move(ke)
            cv.drawArena(game)
        }
        cv.onTimeProgress(50) {
            game = game.step()
            cv.drawArena(game)
        }
    }
    onFinish { }
}