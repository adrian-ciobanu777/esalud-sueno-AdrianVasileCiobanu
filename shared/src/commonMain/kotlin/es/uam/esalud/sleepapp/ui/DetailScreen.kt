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

        // EJERCICIO 6
        // Si os atascáis: pistas graduadas en el guion, parte 5.
        //
        // Mostrad aquí, con elementos Text, los datos guardados del registro:
        //   - el horario de la noche, con el formato "23:30 - 07:15"
        //   - el tiempo en cama, usando formatearMinutos(tiempoEnCama)
        //   - los minutos que estuvo despierto
        //   - la calidad percibida, de 1 a 5
        //   - si rutaAudio es null, un aviso de que la grabación fue simulada

        Spacer(Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(Modifier.height(16.dp))

        // EJERCICIO 7
        // Si os atascáis: pistas graduadas en el guion, parte 5.
        //
        // Calculad y mostrad la eficiencia del sueño.
        //
        //   tiempo dormido = tiempo en cama - minutos despierto
        //
        // Usad la función eficiencia() que escribisteis en la sesión 1, y
        // formatearPorcentaje() para mostrar el resultado.
        //
        // Añadid debajo un texto que avise si está por debajo del 85 %, el
        // umbral orientativo por debajo del cual se considera que puede haber
        // un problema de sueño.

        Spacer(Modifier.height(32.dp))

        OutlinedButton(onClick = onVolver) {
            Text("Volver")
        }
    }
}
