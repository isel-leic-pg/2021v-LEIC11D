import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestDaysOfYear {
    @Test
    fun daysOfYearMiddleMonth() {
        var accumulator = 0
        listOf(0,31,28,31,30,31,30,31,31,30,31,30).map {  accumulator+=it; accumulator }.forEachIndexed { idx, days ->
            val month = idx+1
            val dt = Date(2021, month, idx + 2)
            assertEquals(dt.day + days, dt.toDayOfYear(), "day in month $month")
        }
    }
    @Test
    fun daysOfLeapYearOrNot() {
        assertEquals(15 + 31 + 29, Date(2020, 3, 15).toDayOfYear(), "days in leap year")
        assertEquals(15 + 31 + 28, Date(2021, 3, 15).toDayOfYear(), "days in normal year")
    }
}