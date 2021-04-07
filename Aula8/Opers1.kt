
fun opPlus() {
	val a = readInt("A")
	val b = readInt("B")
	val res = a+b
	println("$a + $b = $res")	
}

fun opMinus() {
	val a = readInt("A")
	val b = readInt("B")
	val res = a-b
	println("$a - $b = $res")	
}

fun opMultiply() {
	val a = readInt("A")
	val b = readInt("B")
	val res = a*b
	println("$a x $b = $res")	
}

fun main() {
	opPlus()
	opMinus()
	opMultiply()
}
