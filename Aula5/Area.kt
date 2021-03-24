
fun main() {
	print("Raio? ")
	val line = readLine()!!
	val cline = line.trim()
	println("|$cline|")
	val r = cline.toDouble()
	val area = r * r * 3.14159
	println("Area = $area")
}


