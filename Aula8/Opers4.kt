

fun opOperator(op:Char, f :(Int,Int)->Int ) {
	val a = readInt("A")
	val b = readInt("B")
	val res = f(a,b)
	println("$a $op $b = $res")	
}

fun main() {

	opOperator('+', {a,b -> a+b} )
	opOperator('-') {a,b-> a-b }
	opOperator('x') { a,b-> 
	   a * b
	}
}
