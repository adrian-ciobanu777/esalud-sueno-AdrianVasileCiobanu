package es.uam.esalud.sleepapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.painterResource
import sleepapp.shared.generated.resources.Res
import sleepapp.shared.generated.resources.icono

@Composable
fun iconoApp(): Painter = painterResource(Res.drawable.icono)