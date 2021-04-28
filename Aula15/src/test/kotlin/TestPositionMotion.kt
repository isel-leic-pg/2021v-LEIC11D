import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestPositionMotion {
    @Test
    fun createPosition() {
        val p = Position(2,3)
        assertEquals(2,p.x,"x property")
        assertEquals(3,p.y,"y property")
    }
    @Test
    fun createMotion() {
        val m = Motion(2,-3)
        assertEquals("Motion(dx=2, dy=-3)",m.toString(),"motion creation")
    }
    @Test
    fun plusPositionMotion() {
        val p = Position(2,3) + Motion(2,-3)
        assertEquals(Position(4,0) , p, "Position + Motion")
    }
    @Test
    fun generateRandomMotion() {
        val m = randomMotion()
        //println(m)
        assertTrue(m.dx in VELOCITY_LIMITS && m.dy in VELOCITY_LIMITS,"motion in range")
    }
}