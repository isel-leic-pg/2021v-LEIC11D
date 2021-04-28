import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestBall {
    @Test
    fun createBall() {
        val b = Ball(Position(10,10),Motion(4,-2),10)
        val str = b.toString()
        assertEquals("Ball(position=Position(x=10, y=10), motion=Motion(dx=4, dy=-2), radius=10)",str,"Create Ball")
    }
    @Test
    fun moveBallFree() {
        val b = Ball(Position(10,15),Motion(4,-2),10)
        val b2 = b.move(100,100)
        assertTrue(b2!=null)
        if (b2!=null) {
            assertEquals(Position(14, 13), b2.position, "Position after move")
            assertEquals(b.motion, b2.motion, "Motion after move")
            assertEquals(b.radius, b2.radius, "Radius after move")
        }
    }
    @Test
    fun moveBallCollide1() {
        val b = Ball(Position(10,15),Motion(4,2),10)
        val b2 = b.move(20,26)
        assertTrue(b2!=null)
        if (b2!=null) {
            assertEquals(Position(14, 17), b2.position, "Position after move")
            assertEquals(Motion(-4, 2), b2.motion, "Motion after move")
            assertEquals(b.radius, b2.radius, "Radius after move")
        }
    }
    @Test
    fun moveBallCollide2() {
        val b = Ball(Position(10,10),Motion(-4,-2),10)
        val b2 = b.move(100,100)
        assertTrue(b2!=null)
        if (b2!=null) {
            assertEquals(Position(6, 8), b2.position, "Position after move")
            assertEquals(Motion(4, 2), b2.motion, "Motion after move")
            assertEquals(b.radius, b2.radius, "Radius after move")
        }
    }
    @Test
    fun moveBallCollide3() {
        val b = Ball(Position(10,20),Motion(-4,2),10)
        val b2 = b.move(100,10)
        assertTrue(b2==null)
    }
    @Test
    fun processKey_R() {
        val b = Ball(Position(10,15),Motion(4,-2),10)
        val b2 = b.processKey('R')
        assertEquals(11,b2.radius,"radius after R")
        assertEquals(b.position,b2.position,"Equal position after R")
        assertEquals(b.motion,b2.motion,"Equal motion after R")
    }
    @Test
    fun processKey_r() {
        val b = Ball(Position(10,15),Motion(4,-2),10)
        val b2 = b.processKey('r')
        assertEquals(b,b2,"Ball after r")
    }
}