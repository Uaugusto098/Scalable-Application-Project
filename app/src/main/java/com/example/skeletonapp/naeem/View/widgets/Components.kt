package com.example.skeletonapp.naeem.View.widgets

// Componentes visuais só do app Naeem (campos dourados e botão de imagem).


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Router
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skeletonapp.R


@Composable
fun TextLightNaeem(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    placeholder: String
) {
    val Dourado       = Color(0xFFC9A96E)
    val DouradoClaro  = Color(0xFFE8D3A0)
    val Creme         = Color(0xFFF3E6CC)
    val MarromEscuro  = Color(0xFF1E120D)
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.width(150.dp).height(46.dp),
        placeholder = { Text(text = label, fontSize = 7.sp) },
        singleLine = true,
        textStyle = TextStyle(fontSize = 7.sp),
        leadingIcon = { Icon(Icons.Outlined.Router, contentDescription = null) },
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor   = MarromEscuro.copy(alpha = 0.55f),
            unfocusedContainerColor = MarromEscuro.copy(alpha = 0.35f),

            // borda: dourado forte quando focado, dourado apagado parado
            focusedBorderColor   = DouradoClaro,
            unfocusedBorderColor = Dourado.copy(alpha = 0.45f),

            // texto digitado
            focusedTextColor   = Creme,
            unfocusedTextColor = Creme,
            cursorColor        = DouradoClaro,

            // ícone
            focusedLeadingIconColor   = DouradoClaro,
            unfocusedLeadingIconColor = Dourado.copy(alpha = 0.7f),

            // placeholder
            focusedPlaceholderColor   = Dourado.copy(alpha = 0.5f),
            unfocusedPlaceholderColor = Dourado.copy(alpha = 0.7f),

            // label (se usar label = { ... })
            focusedLabelColor   = DouradoClaro,
            unfocusedLabelColor = Dourado
        )
    )
}



@Composable
fun BotaoImagem(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tamanhoImagem: Dp = 100.dp
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressionado by interactionSource.collectIsPressedAsState()

    // Escurecimento: 0 = normal, 0.4 = 40% mais escuro
    val escurecer by animateFloatAsState(
        targetValue = if (pressionado) 0.4f else 0f,
        animationSpec = tween(100),
        label = "escurecer"
    )
    // Encolhe um pouco ao apertar
    val escala by animateFloatAsState(
        targetValue = if (pressionado) 0.93f else 1f,
        animationSpec = tween(100),
        label = "escala"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .graphicsLayer {
                scaleX = escala
                scaleY = escala
            }
            .clickable(
                interactionSource = interactionSource,
                indication = null, // tira a mancha cinza padrão
                enabled = enabled,
                onClick = onClick
            )
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.buttonnaeem),
            contentDescription = texto,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(
                Color.Black.copy(alpha = escurecer),
                BlendMode.SrcAtop // escurece só a imagem, não o fundo transparente
            ),
            modifier = Modifier.size(tamanhoImagem)
        )
        Text(
            text = texto.uppercase(),
            color = if (pressionado) Color(0xFFE8D3A0) else Color(0xFFD9B27C),
            fontSize = 13.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = 2.5.sp,
            maxLines = 1,
            softWrap = false,
            // fontFamily = FontFamily(Font(R.font.montserrat_light))
        )
    }
}







