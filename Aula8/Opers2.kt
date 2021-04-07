

fun opOperator(op:Char) {
	val a = readInt("A")
	val b = readInt("B")
	val res = when (op) {
	  '+' -> a+b
	  '-' -> a-b
	  'x' -> a*b
	  else -> 0   // Code smell
	}
	println("$a $op $b = $res")	
}

fun main() {
	opOperator('+')
	opOperator('-')
	opOperator('x')
}
