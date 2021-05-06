import pt.isel.canvas.*

const val CELL_SIDE = 128
const val GRID_WIDTH = 6
const val GRID_HEIGHT = 5
const val ANIM_STEPS = 8  // Multiple of CELL_SIDE

const val SPRITE_DIV = 64
const val SPRITE_CELLS = 4

/**
 * @property x final grid position in horizontal
 * @property y final grid position in vertical
 * @property stepAnim step of animation 0..CELL_SIDE (0->final position; CELL_SIDE->start position)
 * @property dx delta x position (-1;0;+1)
 * @property dy delta y position (-1;0;+1)
 */
data class Hero(val x:Int, val y:Int, val stepAnim:Int, val dx:Int, val dy:Int)

fun Canvas.drawVirus(h:Hero) {
    erase()
    drawGrid()
    val x = h.x * CELL_SIDE - h.stepAnim * h.dx
    val y = h.y * CELL_SIDE - h.stepAnim * h.dy
    val yImg = SPRITE_DIV * when {
        h.dx<0 && h.dy==0 -> 1
        h.dx>0 && h.dy==0 -> 2
        h.dx==0 && h.dy<0 -> 3
        else -> 0
    }
    println("${h.stepAnim} ${(h.stepAnim/(CELL_SIDE/ANIM_STEPS))}")
    val xImg = SPRITE_DIV * ((h.stepAnim/(CELL_SIDE/ANIM_STEPS))  % SPRITE_CELLS)
    drawImage("Hero.png|$xImg,$yImg,$SPRITE_DIV,$SPRITE_DIV", x , y , CELL_SIDE, CELL_SIDE)
}

fun move(key :Int, v:Hero) :Hero = when {
    key==UP_CODE    && v.y>0                -> Hero(v.x, v.y-1, CELL_SIDE, 0, -1)
    key==DOWN_CODE  && v.y<GRID_HEIGHT-1    -> Hero(v.x, v.y+1, CELL_SIDE, 0, +1)
    key==LEFT_CODE  && v.x>0                -> Hero(v.x-1, v.y, CELL_SIDE, -1, 0)
    key==RIGHT_CODE && v.x<GRID_WIDTH-1     -> Hero(v.x+1, v.y, CELL_SIDE, +1, 0)
    else -> v
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
        var virus = Hero( GRID_WIDTH/2, GRID_HEIGHT/2, stepAnim = 0, dx= 0, dy= 0)
        cv.drawVirus(virus)
        cv.onKeyPressed { ke :KeyEvent ->
            virus = move(ke.code,virus)
            cv.drawVirus(virus)
        }
        cv.onTimeProgress(500) {
            if (virus.stepAnim>0) { // está em movimento
                virus = virus.copy( stepAnim = virus.stepAnim - CELL_SIDE/ANIM_STEPS)
                cv.drawVirus(virus)
            }
        }
    }
    onFinish {  }
}
