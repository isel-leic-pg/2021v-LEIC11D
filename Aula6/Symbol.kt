
fun main() {
  val DIF = 'a'-'A'
  print("Simbolo? ")
  val symb = readLine()!!.first()
  
  if ( symb in '0'..'9' )
	println("Digito")
  else
	if ( symb in 'A'..'Z' || symb in 'a'..'z' ) {
		println("Letra")
	    if (symb in 'a'..'z') {
			val maius = symb - DIF
			println("Maiuscula=$maius")
		} else
			println("Minuscula=${symb-('A'-'a')}")
	} 
		
  println("Fim")
}