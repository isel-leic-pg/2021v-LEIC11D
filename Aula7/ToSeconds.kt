

fun toSeconds(hours :Int =0, mins :Int =0, secs :Int =0) = 3600 * hours + 60 * mins + secs


fun main() :Unit {
	val h = readInt("Horas")
	val m = readInt("Minutos")
	val s = readInt("Segundos")
	val secs = toSeconds(h,m,s)
	
	println (
		if (h>=0 && m in 0..59 && s in 0..59) "$h:$m:$s = $secs segs."   //2:23:47 = .... segs.
		else "Tempo inválido."
	)
}