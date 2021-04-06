val PI = 3.14159

fun circleArea(radius :Double) :Double = PI * radius * radius

fun main() {
    val r = readDouble("Raio")
	val area = circleArea(r)
	println("Area = $area")
}


