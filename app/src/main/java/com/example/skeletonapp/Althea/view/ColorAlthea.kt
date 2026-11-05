package com.example.skeletonapp.Althea.view
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.skeletonapp.Model.api.CoresDto

// defina as cores do seu projeto como bem entender
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

//MITRE ATHEA COLORS

val TextButton=Color(0xffFFFFFF)
val TextPressed=Color(0xFF52647A)

val buttonColor=Color(0xff03271D)
val ButtonPressed=Color(0xff065741)

val numero=0xff000000
val colorBorda=Color(numero)

private fun String.paraCor(padrao: Color): Color =
    runCatching { Color(android.graphics.Color.parseColor(this)) }.getOrDefault(padrao)

/** Cores atuais do app. Começam com as suas cores e são trocadas quando o JSON chega. */
object PaletaRemota {
    var normal by mutableStateOf(ButtonPressed)
    var pressionado by mutableStateOf(buttonColor)
    var texto by mutableStateOf(TextButton)
    var borda by mutableStateOf(colorBorda)

    fun aplicar(c: CoresDto) {
        normal = c.botao.paraCor(ButtonPressed)
        pressionado = c.botaoPressionado.paraCor(buttonColor)
        texto = c.texto.paraCor(TextButton)
        borda = c.borda.paraCor(colorBorda)
    }
}

