package es.uam.esalud.sleepapp.logica

fun duracionEnMinutos(inicio: Hora, fin: Hora): Int {
    val inicioMinutos = inicio.desdeMedianoche()
    val finMinutos = fin.desdeMedianoche()

    return if (finMinutos >= inicioMinutos) {
        finMinutos - inicioMinutos
    } else {
        (24 * 60 - inicioMinutos) + finMinutos
    }
}

/**
 * Eficiencia del sueño: porcentaje del tiempo en cama que se ha pasado dormido.
 *
 * @throws IllegalArgumentException si [minutosEnCama] no es positivo.
 */
fun eficiencia(minutosDormido: Int, minutosEnCama: Int): Double {
    TODO("Ejercicio 2")
}
