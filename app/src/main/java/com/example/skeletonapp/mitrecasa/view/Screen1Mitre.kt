package com.example.skeletonapp.mitrecasa.view

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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.sp
import com.example.skeletonapp.Althea.view.widgets.ButtonEdit
import com.example.skeletonapp.Althea.view.widgets.TextLight
import com.example.skeletonapp.R
import com.example.skeletonapp.mitrecasa.viewmodel.Screen1UiStateMitre



private val LarguraMaxConteudo = 1100.dp
private val LarguraLogo = 0.32f
private val AlturaMaxLogo = 260.dp
private val EspacoLogoBotoes = 48.dp
private val EspacoEntreBotoes = 24.dp
private val MargemLateral = 48.dp
private val LarguraRodape = 0.20f
private val AlturaMaxRodape = 70.dp
private val MargemInferior = 45.dp


@Composable
fun MainScreenMitre(
    ipChange: (String) -> Unit,
    portChange: (String) -> Unit,
    oscClick: (String) -> Unit,
    stateScreen: Screen1UiStateMitre
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
            .paint(painterResource(R.drawable.back_bege), contentScale = ContentScale.Crop)
            .pointerInput(Unit) { detectTapGestures(onTap = { focusManager.clearFocus() }) }
    ) {

        Column(
            modifier = Modifier.fillMaxSize().systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.weight(1f))

            Image(
                painter = painterResource(R.drawable.logocasamain),
                contentDescription = "Mitre Casa",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth(LarguraLogo)
                    .heightIn(max = AlturaMaxLogo)
            )

            Spacer(Modifier.height(EspacoLogoBotoes))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(EspacoEntreBotoes, Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(EspacoEntreBotoes),
                modifier = Modifier
                    .widthIn(max = LarguraMaxConteudo)
                    .fillMaxWidth()
                    .padding(horizontal = MargemLateral)
            ) {
                for (botao in stateScreen.botoes) {
                    ButtonEdit(
                        onClick = { oscClick(botao.endereco) },
                        text = botao.nome,
                        enabled = stateScreen.connectionVerify,
                        modifier = Modifier.width(botao.larguraDp.dp).height(botao.alturaDp.dp),
                        fontSize = 18.sp
                    )
                }
            }

            Spacer(Modifier.weight(1f))

            Image(
                painter = painterResource(R.drawable.rodape),
                contentDescription = "Basilar | Mitre",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth(LarguraRodape)
                    .heightIn(max = AlturaMaxRodape)
                    .padding(bottom = MargemInferior)
            )
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
                    TextLight(
                        value = stateScreen.ip.trim(),
                        onValueChange = ipChange,
                        placeholder = "Digite o IP aqui",
                        label = "IP",
                    )
                    TextLight(
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
                    tint = Color.Black,
                    modifier = Modifier.graphicsLayer { rotationZ = rotacaoEngrenagem }
                )
            }
        }
    }
}
