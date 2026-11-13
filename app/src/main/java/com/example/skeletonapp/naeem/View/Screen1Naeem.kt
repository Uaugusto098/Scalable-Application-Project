package com.example.skeletonapp.naeem.View

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.skeletonapp.R
import com.example.skeletonapp.naeem.View.widgets.BotaoImagem
import com.example.skeletonapp.naeem.View.widgets.TextLightNaeem
import com.example.skeletonapp.naeem.ViewModel.Screen1UiStateNaeem


private val BaseLinhaCima = 780.dp
private val BaseLinhaBaixo = 522.dp
private val PassoLinhaExtra = 258.dp
private const val BotoesPorLinha = 3
private val LarguraMinSlot = 120.dp
private val EspacoEntreBotoes = 56.dp
private val TamanhoImagemBotao = 100.dp
private val CorEngrenagem = Color(0xFFD9B27C)


@Composable
fun MainScreenNaeem(
    stateScreen: Screen1UiStateNaeem,
    ipChange: (String) -> Unit,
    portChange: (String) -> Unit,
    oscClick: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    var inputsMode by remember { mutableStateOf(false) }

    val rotacaoEngrenagem by animateFloatAsState(
        targetValue = if (inputsMode) 90f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "rotacaoEngrenagem"
    )

    Box(
        Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.telanaeem), contentScale = ContentScale.Crop)
            .pointerInput(Unit) { detectTapGestures(onTap = { focusManager.clearFocus() }) }
    ) {

        // ---------- Botões: 3 por linha, na ordem em que vêm do JSON ----------
        val linhas = stateScreen.botoes.chunked(BotoesPorLinha)

        linhas.forEachIndexed { indice, linha ->
            val base = when (indice) {
                0 -> BaseLinhaCima
                1 -> BaseLinhaBaixo
                else -> BaseLinhaBaixo - PassoLinhaExtra * (indice - 1)
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(EspacoEntreBotoes),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = base.coerceAtLeast(0.dp))
            ) {
                for (botao in linha) {
                    Box(
                        Modifier.widthIn(min = LarguraMinSlot),
                        contentAlignment = Alignment.Center
                    ) {
                        BotaoImagem(
                            texto = botao.nome,
                            enabled = stateScreen.connectionVerify,
                            tamanhoImagem = TamanhoImagemBotao,
                            onClick = { oscClick(botao.endereco) }
                        )
                    }
                }
            }
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .statusBarsPadding()
                .padding(top = 20.dp, end = 32.dp)
        ) {
            AnimatedVisibility(
                visible = inputsMode,
                enter = expandHorizontally(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMediumLow
                    ),
                    expandFrom = Alignment.End
                ) + slideInHorizontally(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessMediumLow
                    ),
                    initialOffsetX = { it / 3 }
                ) + fadeIn(animationSpec = tween(durationMillis = 250, delayMillis = 50)),
                exit = shrinkHorizontally(
                    animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
                    shrinkTowards = Alignment.End
                ) + slideOutHorizontally(
                    animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
                    targetOffsetX = { it / 3 }
                ) + fadeOut(animationSpec = tween(durationMillis = 150))
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(18.dp),
                    modifier = Modifier.padding(end = 18.dp)
                ) {
                    TextLightNaeem(
                        value = stateScreen.ip.trim(),
                        onValueChange = ipChange,
                        placeholder = "Digite o IP aqui",
                        label = "IP",
                    )
                    TextLightNaeem(
                        value = stateScreen.port.trim(),
                        onValueChange = portChange,
                        placeholder = "Digite a porta aqui",
                        label = "Porta",
                    )
                }
            }

            IconButton(onClick = {
                inputsMode = !inputsMode
                if (!inputsMode) focusManager.clearFocus()
            }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Configurações",
                    tint = CorEngrenagem,
                    modifier = Modifier.graphicsLayer { rotationZ = rotacaoEngrenagem }
                )
            }
        }
    }
}
