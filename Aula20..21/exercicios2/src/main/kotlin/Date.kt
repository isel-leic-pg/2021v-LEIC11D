/**
 * Stores a date in the Gregorian calendar.
 * [(wikipedia)](https://pt.wikipedia.org/wiki/Calend%C3%A1rio_gregoriano)
 *
 * year: 1583..3000; month:1..12; day:1..31
 */
data class Date(val year:Int, val month:Int, val day:Int)

fun isLeapYear(year: Int) :Boolean = year%400 == 0 || year%4 == 0 && year%100 !=0

fun maxDaysOfMonth(month: Int, year: Int) :Int = when (month) {
    4, 6, 9, 11 -> 30
    2 -> if (isLeapYear(year)) 29 else 28
    else -> 31
}

/**
 *  Checks whether the date is valid.
 *  @receiver The date to check
 *  @return true if the date is valid
 */
fun Date.isValid() :Boolean = year in 1583..3000 && month in 1..12 && day in 1..maxDaysOfMonth(month,year)

/**
 * Given a date, it returns the number of days since the beginning of the year for that date.
 * @receiver Date to use
 * @return Day of year
 */
fun Date.toDayOfYear() :Int = (1 until month).sumOf { maxDaysOfMonth(it, year) } + day
/*{
    var sum = day
    (1 until month).forEach { sum += maxDaysOfMonth(it,year) }
    return sum
}*/