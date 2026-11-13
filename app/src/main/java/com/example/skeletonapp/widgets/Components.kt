package com.example.skeletonapp.widgets




import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Router
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skeletonapp.Althea.view.PaletaRemota
import kotlinx.coroutines.launch


@Composable
fun ButtonEdit(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    fontSize: TextUnit = 30.sp,
    textColors: Color = PaletaRemota.texto,
    corBorda: Color = PaletaRemota.borda,
    corOnda: Color = PaletaRemota.borda,
    onda: Boolean = true,
    corNormal: Color = PaletaRemota.normal,
    corPressionado: Color = PaletaRemota.pressionado,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val cor by animateColorAsState(
        targetValue = if (isPressed) corPressionado else corNormal,
        label = "corBotao"
    )
    val desabilitado = textColors.copy(alpha = 0.4f)

    val progresso = remember { Animatable(1f) }
    val scope = rememberCoroutineScope()
    LaunchedEffect(isPressed) {
        if (isPressed && onda) {
            scope.launch {
                progresso.snapTo(0f)
                progresso.animateTo(1f, tween(550, easing = FastOutSlowInEasing))
            }
        }
    }

    Button(
        onClick = onClick,
        enabled = enabled,
        interactionSource = interactionSource,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, if (enabled) corBorda else corBorda.copy(alpha = 0.4f)),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 2.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = cor,
            contentColor = textColors,
            disabledContainerColor = corNormal.copy(alpha = 0.45f),
            disabledContentColor = desabilitado
        ),
        modifier = modifier.drawBehind {
            val p = progresso.value
            if (p < 1f) {
                val expansao = 18.dp.toPx() * p
                drawRoundRect(
                    color = corOnda.copy(alpha = 0.85f * (1f - p)),
                    topLeft = Offset(-expansao, -expansao),
                    size = Size(size.width + 2 * expansao, size.height + 2 * expansao),
                    cornerRadius = CornerRadius(10.dp.toPx() + expansao),
                    style = Stroke(width = 1.dp.toPx() + 3.dp.toPx() * (1f - p))
                )
            }
        }
    ) {
        Text(
            text = text,
            color = if (enabled) textColors else desabilitado,
            fontStyle = FontStyle.Italic,
            fontSize = fontSize,
            maxLines = 1,
            modifier = Modifier.padding(all = 10.dp)
        )
    }
}


    @Composable
    fun TextLight(
        value: String,
        onValueChange: (String) -> Unit,
        label: String,
        keyboardType: KeyboardType = KeyboardType.Text,
        placeholder: String
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .width(150.dp)
                .height(46.dp),
            placeholder = { Text(text = label, fontSize = 7.sp) },
            singleLine = true,
            textStyle = TextStyle(fontSize = 7.sp),
            leadingIcon = { Icon(Icons.Outlined.Router, contentDescription = null) },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0x33FFFFFF),
                unfocusedContainerColor = Color(0x22FFFFFF),
                focusedBorderColor = Color(0xFF797675),
                unfocusedBorderColor = Color(0x66EDE4D3),
                focusedTextColor = Color(0xFF000000),
                unfocusedTextColor = Color(0xFF000000),
                cursorColor = Color(0xFF000000),
                focusedLeadingIconColor = Color(0xFF000000),
                unfocusedLeadingIconColor = Color(0xCC000000),
                focusedPlaceholderColor = Color(0xFFFFFFFF),
                unfocusedPlaceholderColor = Color(0xFF000000)
            )
        )
    }

    @Composable
    fun ImagemRevelada(
        imagem: Int,
        modifier: Modifier = Modifier,
        imagemCentro: Int? = null,
        tamanhoCentro: Dp = 220.dp,
        duracao: Int = 750,
        larguraBorda: Float = 0.35f,
        contentScale: ContentScale = ContentScale.Fit
    ) {
        var fundoAnterior by remember { mutableIntStateOf(imagem) }
        var fundoAtual by remember { mutableIntStateOf(imagem) }
        var centroAnterior by remember { mutableStateOf(imagemCentro) }
        var centroAtual by remember { mutableStateOf(imagemCentro) }
        val progresso = remember { Animatable(1f) }

        LaunchedEffect(imagem, imagemCentro) {
            if (imagem != fundoAtual || imagemCentro != centroAtual) {
                fundoAnterior = fundoAtual
                fundoAtual = imagem
                centroAnterior = centroAtual
                centroAtual = imagemCentro
                progresso.snapTo(0f)
                progresso.animateTo(1f, tween(duracao, easing = FastOutSlowInEasing))
            }
        }

        val centroMudou = centroAnterior != centroAtual

        Box(modifier, contentAlignment = Alignment.Center) {

            Image(
                painter = painterResource(fundoAnterior),
                contentDescription = null,
                contentScale = contentScale,
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer { alpha = 1f - progresso.value }
            )


            Image(
                painter = painterResource(fundoAtual),
                contentDescription = null,
                contentScale = contentScale,
                modifier = Modifier.revelarDaEsquerda({ progresso.value }, larguraBorda)
            )
        }



            if (centroMudou) {
                centroAnterior?.let { img ->
                    Image(
                        painter = painterResource(img),
                        contentDescription = null,
                        modifier = Modifier
                            .size(tamanhoCentro)
                            .graphicsLayer {
                                alpha = 1f - (progresso.value / 0.4f).coerceIn(0f, 1f)
                            }
                            .offset(y = -20.dp)
                    )
                }
            }

            centroAtual?.let { img ->
                Image(
                    painter = painterResource(img),
                    contentDescription = "Imagem central",
                    modifier = Modifier
                        .size(tamanhoCentro)
                        .graphicsLayer {
                            val t = if (centroMudou)
                                ((progresso.value - 0.3f) / 0.7f).coerceIn(0f, 1f)
                            else 1f
                            alpha = t
                            scaleX = 0.9f + 0.1f * t
                            scaleY = 0.9f + 0.1f * t
                        }
                        .offset(y = -20.dp)
                        .size(width = 600.dp, height = 215.dp)
                )
            }
        }


    fun Modifier.revelarDaEsquerda(progresso: () -> Float, larguraBorda: Float = 0.35f) =
        this
            .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
            .drawWithContent {
                drawContent()
                val borda = size.width * larguraBorda
                val frente = -borda + (size.width + borda) * progresso()
                drawRect(
                    brush = Brush.horizontalGradient(
                        0f to Color.Black,
                        1f to Color.Transparent,
                        startX = frente,
                        endX = frente + borda
                    ),
                    blendMode = BlendMode.DstIn
                )
            }
