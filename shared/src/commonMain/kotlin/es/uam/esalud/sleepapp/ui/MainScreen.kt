package es.uam.esalud.sleepapp.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.uam.esalud.sleepapp.audio.GrabadorAudio
import es.uam.esalud.sleepapp.nombrePlataforma

@Composable
fun MainScreen(grabador: GrabadorAudio) {

    // `remember` + `mutableStateOf` = una variable que, al cambiar, hace que
    // Compose vuelva a dibujar lo que dependa de ella.
    var grabando by remember { mutableStateOf(false) }
    var ultimaRuta by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

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
                    grabando = true
                    // TODO (1): llamar a grabador.iniciar(), guardar la ruta que
                    //           devuelve en ultimaRuta y actualizar `grabando`.
                },
                // TODO (2): ¿cuándo debe estar activo este botón?
                //           Pista: no tiene sentido iniciar dos veces seguidas.
                enabled =! grabando
            ) {
                Text("Iniciar grabación")
            }

            Button(
                onClick = {
                    grabador.detener()
                    grabando = false
                },
                enabled = grabando
            ) {
                Text("Detener grabación")
            }

            // TODO (3): añadid aquí el botón de detener, simétrico al anterior.
            //           Debe llamar a grabador.detener() y dejar `grabando` a false.
        }

        Spacer(Modifier.height(32.dp))

        val mensaje = if (grabando) "Grabando..." else "En reposo"
        Text(text = mensaje, style = MaterialTheme.typography.bodyLarge)


        // TODO (4): mostrad un texto distinto según el valor de `grabando`
        //           ("Grabando..." o "En reposo"), y debajo la última ruta
        //           grabada si existe.
        //           Pista: en Kotlin, `if` devuelve un valor:
        //               val mensaje = if (grabando) "A" else "B"
    }
}
