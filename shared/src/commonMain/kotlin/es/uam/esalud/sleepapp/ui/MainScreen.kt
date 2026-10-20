package es.uam.esalud.sleepapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.uam.esalud.sleepapp.SuenoViewModel
import es.uam.esalud.sleepapp.audio.GrabadorAudio
import es.uam.esalud.sleepapp.horaActual
import es.uam.esalud.sleepapp.logica.Hora
import es.uam.esalud.sleepapp.nombrePlataforma

/**
 * SESIÓN 4 - PANTALLA PRINCIPAL
 *
 * Añadido: correoUsuario, onCerrarSession (sesión 4) y el diálogo (sesión 3).
 */
@Composable
fun MainScreen(
    grabador: GrabadorAudio,
    viewModel: SuenoViewModel,
    correoUsuario: String,
    onCerrarSession: () -> Unit,
    onRegistroClick: (Long) -> Unit
) {

    var grabando by remember { mutableStateOf(false) }
    var ultimaRuta by remember { mutableStateOf<String?>(null) }
    var horaInicio by remember { mutableStateOf(Hora(0, 0)) }
    var horaFin by remember { mutableStateOf(Hora(0, 0)) }
    var mostrarDialogo by remember { mutableStateOf(false) }

    val registros by viewModel.registros.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        // Fila superior con el correo y el botón de cerrar sesión
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = correoUsuario,
                style = MaterialTheme.typography.bodySmall
            )
            TextButton(onClick = onCerrarSession) {
                Text("Cerrar sesión")
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Registro de sueño",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Ejecutando en: " + nombrePlataforma(),
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = {
                    ultimaRuta = grabador.iniciar()
                    horaInicio = horaActual()
                    grabando = true
                },
                enabled = !grabando
            ) {
                Text("Iniciar grabación")
            }

            Button(
                onClick = {
                    grabador.detener()
                    grabando = false
                    horaFin = horaActual()
                    mostrarDialogo = true
                },
                enabled = grabando
            ) {
                Text("Detener grabación")
            }
        }

        Spacer(Modifier.height(32.dp))

        val mensaje = if (grabando) "Grabando..." else "En reposo"
        Text(text = mensaje, style = MaterialTheme.typography.bodyLarge)

        ListaRegistros(registros, onRegistroClick = onRegistroClick)
    }

    if (mostrarDialogo) {
        DialogoValoracion(
            inicio = horaInicio,
            fin = horaFin,
            onGuardar = { calidad, despierto ->
                viewModel.guardar(
                    inicio = horaInicio,
                    fin = horaFin,
                    rutaAudio = ultimaRuta,
                    calidadPercibida = calidad,
                    minutosDespierto = despierto
                )
                mostrarDialogo = false
            },
            onCancelar = {
                mostrarDialogo = false
            }
        )
    }
}