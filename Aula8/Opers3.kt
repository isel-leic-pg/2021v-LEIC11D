

fun opOperator(op:Char, f :(Int,Int)->Int ) {
	val a = readInt("A")
	val b = readInt("B")
	val res = f(a,b)
	println("$a $op $b = $res")	
}

fun add(a:Int,b:Int) = a+b
fun sub(a:Int,b:Int) = a-b
fun mult(a:Int,b:Int) = a*b

fun main() {
	opOperator('+',::add)
	opOperator('-',::sub)
	opOperator('x',::mult)
}
