
fun main() {
	print("Valor? ")
	val value = readLine()!!.toInt()
	val positive : Boolean
	if (value>=0) {
		println("Positivo")
		positive = true
	} else {
		println("Negativo")
		positive = false
	}
	println(positive)
}