import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestValidDate {
    @Test
    fun minMaxYear() {
        assertFalse(Date(1580, 1, 1).isValid(), "too old year")
        assertTrue(Date(1583, 1, 1).isValid(), "min year")
        assertTrue(Date(3000, 1, 1).isValid(), "max year")
        assertFalse(Date(3001, 1, 1).isValid(), "too big year")
    }
    @Test
    fun minMaxMonth() {
        assertFalse(Date(2021, 0, 3).isValid(), "month must be greater than zero")
        (1..12).forEach { assertTrue(Date(2021, it, 3).isValid(), "normal valid date") }
        assertFalse(Date(2021, 13, 3).isValid(), "month must be less than 13")
    }
    @Test
    fun limitDays30() {
        listOf(4,6,9,11).forEach {
            assertTrue(Date(2020, it, 30).isValid(), "maximum days in months of 30")
            assertFalse(Date(2020, it, 31).isValid(), "excess days in months of 30")
        }
    }
    @Test
    fun limitDays31() {
        listOf(1,3,5,7,8,10,12).forEach {
            assertTrue(Date(1990, it, 31).isValid(), "maximum days in months of 31")
            assertFalse(Date(1990, it, 32).isValid(), "excess days in months of 31")
        }
    }
    @Test
    fun limitDaysFebruaryLeapYears() {
        ((1980..2030 step 4) + (1600..3000 step 400)).forEach {
            assertTrue(Date(it, 2, 29).isValid(), "last day of February in leap year")
            assertFalse(Date(it, 2, 30).isValid(), "excess days in February in leap year")
        }
    }
    @Test
    fun limitDaysFebruaryNormalYears() {
        ((1600..3000 step 100) - (1600..3000 step 400) + (2001..2003)).forEach {
            assertTrue(Date(it, 2, 28).isValid(), "last day of February in normal year")
            assertFalse(Date(it, 2, 29).isValid(), "excess days in February in normal year")
        }
    }
}