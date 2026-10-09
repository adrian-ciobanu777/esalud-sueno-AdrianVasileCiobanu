package es.uam.esalud.sleepapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.uam.esalud.sleepapp.datos.SleepRecord
import es.uam.esalud.sleepapp.logica.duracionEnMinutos
import es.uam.esalud.sleepapp.logica.eficiencia

/**
 * Pantalla de detalle de un registro.
 *
 * Solo muestra: los datos se introdujeron en el diálogo que aparece al
 * detener la grabación, y aquí se consultan.
 */
@Composable
fun DetailScreen(
    registro: SleepRecord,
    onVolver: () -> Unit
) {
    val tiempoEnCama = duracionEnMinutos(registro.inicio, registro.fin)

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Detalle del registro",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(16.dp))

        // EJERCICIO 6 — Datos del registro
        Text("${registro.inicio} - ${registro.fin}")
        Text("Tiempo en cama: ${formatearMinutos(tiempoEnCama)}")
        Text("Tiempo despierto: ${registro.minutosDespierto} min")
        Text("Calidad percibida: ${registro.calidadPercibida ?: "sin valorar"} / 5")
        if (registro.rutaAudio == null) {
            Text("(grabación simulada)")
        }

        Spacer(Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(Modifier.height(16.dp))

        // EJERCICIO 7 — Eficiencia del sueño
        val tiempoDormido = tiempoEnCama - registro.minutosDespierto
        val porcentaje = eficiencia(
            minutosDormido = tiempoDormido,
            minutosEnCama = tiempoEnCama
        )

        Text("Eficiencia del sueño: ${formatearPorcentaje(porcentaje)}")

        if (porcentaje < 85) {
            Text("Por debajo del 85 %, el umbral orientativo de referencia.")
        }

        Spacer(Modifier.height(32.dp))

        OutlinedButton(onClick = onVolver) {
            Text("Volver")
        }
    }
}