package com.example.skeletonapp.View.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skeletonapp.View.widgets.ButtonEdit
import com.example.skeletonapp.View.widgets.TextLight
import com.example.skeletonapp.ViewModel.UiState.Screen1UiState


// Arquivo responsável pelo design das telas e visualização do sistema


@Composable
fun MainScreen(
    Conectar: () -> Unit,
    stateScreen: Screen1UiState,
    IpChange: (String) -> Unit,
    PortChange: (String) -> Unit,
    OscClick: (String) -> Unit


) {

    val historicoEndereco = remember { mutableListOf<String>() }
    var textoAntigo by rememberSaveable() { mutableStateOf("") }




    Box(modifier = Modifier.fillMaxSize()) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            modifier = Modifier.align(Alignment.TopEnd).statusBarsPadding()
                .padding(top = 20.dp, end = 32.dp)
        )
        {
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
}










