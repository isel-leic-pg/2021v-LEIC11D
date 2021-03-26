
fun main() {
	print("Ano? ")
	val year = readLine()!!.toInt()
	val days = if (year%4==0 && year%100!=0 || year%400==0) 366 else 365
	println("O ano $year tem $days dias.")
}