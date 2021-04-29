fun scratchAboutList() {
    val numbers: List<Int> = listOf(27, 32, 54, 12)
    println(numbers)
    numbers.forEach { number ->
        println("number = $number")
    }
    var sum = 0
    numbers.forEach { sum += it }
    println(sum)
    val evens = numbers.filter { it % 2 == 0 }
    println(evens)
    val mapped = numbers.map { it % 2 == 0 }
    println(mapped)
    val res = numbers + 15
    println(res)
    println(numbers - 54)

    println(numbers[0])
    println(numbers[numbers.size - 1])
    println(numbers.first())
    println(numbers.last())
    println(numbers + numbers.first())
    println(res + numbers)
    println(listOf(0) + numbers)
    println(numbers.first() + numbers.last())
}