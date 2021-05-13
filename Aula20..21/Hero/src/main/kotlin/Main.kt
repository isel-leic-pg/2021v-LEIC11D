import pt.isel.canvas.*

fun main() {
    onStart {
        val cv = Canvas(CELL_SIDE * GRID_WIDTH, CELL_SIDE * GRID_HEIGHT, BLACK)
        var game = createGame()
        cv.drawArena(game)
        cv.onKeyPressed { ke: KeyEvent ->
            game = move(ke.code, game)
            cv.drawArena(game)
        }
        cv.onTimeProgress(500) {
            if (game.stepAnim > 0) { // está em movimento
                game = game.copy(stepAnim = game.stepAnim - CELL_SIDE / ANIM_STEPS)
                cv.drawArena(game)
            }
        }
    }
    onFinish { }
}