import pt.isel.canvas.*

const val VIRUS_SIDE = 64
const val GRID_WIDTH = 12
const val GRID_HEIGHT = 10


data class Virus(val x:Int, val y:Int)

fun Canvas.drawVirus(v:Virus) {
    erase()
    drawGrid()
    drawImage("Virus.png", v.x * VIRUS_SIDE ,v.y * VIRUS_SIDE , VIRUS_SIDE, VIRUS_SIDE)
}

fun move(key :Int, v:Virus) :Virus = when (key) {
    UP_CODE -> Virus(v.x, v.y-1)
    DOWN_CODE -> Virus(v.x, v.y+1)
    LEFT_CODE -> Virus(v.x-1, v.y)
    RIGHT_CODE -> Virus(v.x+1, v.y)
    else -> v
}

fun Canvas.drawGrid() {
    (VIRUS_SIDE..height step VIRUS_SIDE).forEach {
        drawLine(0, it, width, it, WHITE, 1) // horizontal
    }
    (VIRUS_SIDE..width step VIRUS_SIDE).forEach {
        drawLine(it, 0, it, height, WHITE, 1) // vertical
    }
}

fun main() {
    onStart {
        val cv = Canvas(VIRUS_SIDE*GRID_WIDTH, VIRUS_SIDE*GRID_HEIGHT, BLACK)
        var virus = Virus( GRID_WIDTH/2, GRID_HEIGHT/2 )
        cv.drawVirus(virus)
        cv.onKeyPressed { ke :KeyEvent ->
            virus = move(ke.code,virus)
            cv.drawVirus(virus)
        }
    }
    onFinish {  }
}
