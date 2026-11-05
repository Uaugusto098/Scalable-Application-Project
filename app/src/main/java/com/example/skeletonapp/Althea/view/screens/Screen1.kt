package com.example.skeletonapp.Althea.view.screens

import NameCues
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.sp
import com.example.skeletonapp.R
import com.example.skeletonapp.Althea.view.widgets.ButtonEdit
import com.example.skeletonapp.Althea.view.widgets.TextLight
import com.example.skeletonapp.Althea.ViewModel.UiState.Screen1UiState
import kotlinx.coroutines.flow.Flow


// Arquivo responsável pelo design das telas e visualização do sistema


@Composable
fun MainScreen(
    Conectar: () -> Unit,
    stateScreen: Screen1UiState,
    IpChange: (String) -> Unit,
    PortChange: (String) -> Unit,
    OscClick: (String) -> Unit
) {

    val focusManager = LocalFocusManager.current
    var InputsMode by remember { mutableStateOf(false) }

    // Engrenagem gira 90° ao abrir e volta ao fechar, com uma leve "acomodada" no fim


    val rotacaoEngrenagem by animateFloatAsState(
        targetValue = if (InputsMode) 90f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "rotacaoEngrenagem"
    )

    Box(
        Modifier.fillMaxSize().paint(
            painterResource(R.drawable.background_althea),
            contentScale = ContentScale.Crop
        )
    ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .statusBarsPadding()
                .padding(top = 20.dp, end = 32.dp)
                .pointerInput(Unit) { detectTapGestures(onTap = { focusManager.clearFocus() }) }
        ) {

            // Campos: abrem para a esquerda a partir da engrenagem
            AnimatedVisibility(
                visible = InputsMode,
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
                    TextLight(
                        value = stateScreen.ip.trim(),
                        onValueChange = IpChange,
                        placeholder = "Digite o IP aqui",
                        label = "IP",
                    )
                    TextLight(
                        value = stateScreen.port.trim(),
                        onValueChange = PortChange,
                        placeholder = "Digite a porta aqui",
                        label = "Porta",
                    )
                }
            }


            IconButton(onClick = {
                InputsMode = !InputsMode
                if (!InputsMode) focusManager.clearFocus()
            }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Configurações",
                    tint = Color.Black,
                    modifier = Modifier.graphicsLayer { rotationZ = rotacaoEngrenagem }
                )
            }
        }

        Box(Modifier.width(1100.dp).height(500.dp).align(Alignment.Center)) {

            Image(
                painterResource(R.drawable.logoalthea),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center).width(300.dp).height(300.dp)
                    .offset(y = -60.dp)
            )

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.align(Alignment.BottomCenter).offset(y = -25.dp, x = -5.dp)
            ) {
                for (botao in stateScreen.botoes) {
                    ButtonEdit(
                        onClick = { OscClick(botao.endereco) },
                        text = botao.nome,
                        enabled = stateScreen.connectionVerify,
                        modifier = Modifier.width(botao.larguraDp.dp).height(botao.alturaDp.dp),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}
