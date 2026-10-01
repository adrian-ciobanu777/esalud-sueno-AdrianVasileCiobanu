package es.uam.esalud.sleepapp.logica

fun duracionEnMinutos(inicio: Hora, fin: Hora): Int {
    val minutosInicio = inicio.desdeMedianoche()
    val minutosFin = fin.desdeMedianoche()

    return if (minutosFin >= minutosInicio) {minutosFin-minutosInicio} // sueño empieza tras 00:00
        else {1440 - minutosInicio + minutosFin}  // sueño empieza en 23:59 o antes
}

/**
 * Eficiencia del sueño: porcentaje del tiempo en cama que se ha pasado dormido.
 *
 * @throws IllegalArgumentException si [minutosEnCama] no es positivo.
 */
fun eficiencia(minutosDormido: Int, minutosEnCama: Int): Double {
    // El tiempo en cama debe de ser mayor que 1.
    require(minutosEnCama>0) {"Inválido. Usted debe de estar en cama más de 0 minutos"}
    // Devolvemos el ratio de tiempo dormido. Ponemos minutos dormido en decimal pa que salga bien
    return (minutosDormido.toDouble() / minutosEnCama) * 100.0
}
