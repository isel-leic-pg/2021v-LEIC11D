import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestNothing {
    @Test
    fun test1() {
        val str = "IS"+"EL"
        assertEquals("ISEL",str,"String ISEL")
    }
    @Test
    fun test2() {
        val range = 0..20
        assertTrue(15 in range,"Range test")
    }
}