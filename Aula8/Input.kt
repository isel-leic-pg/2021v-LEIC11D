

fun readLine(q:String) :String {
	print("$q? ")
	return readLine()!!
}

fun readDouble(quest:String) = readLine(quest).toDouble()

fun readInt(quest:String) = readLine(quest).toInt()
