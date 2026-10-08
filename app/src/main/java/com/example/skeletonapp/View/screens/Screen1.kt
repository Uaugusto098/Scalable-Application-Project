package com.example.skeletonapp.View.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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

    val historicoEndereco= remember { mutableListOf<String>() }
    var textoAntigo by rememberSaveable() {mutableStateOf("") }


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue)
    ) {


        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = stateScreen.ip.trim(),
                onValueChange = IpChange,
                label = { Text("Digite o IP aqui: ") })
            OutlinedTextField(
                value = stateScreen.port,
                onValueChange = PortChange,
                label = { Text("Digite a Porta aqui: ") })
            ButtonEdit(
                onClick = {Conectar()

                },
                text = "Ajuda",
                modifier = Modifier.offset(y = 30.dp),
                textColors = Color.Black,
                enabled = stateScreen.connectionVerify
            )
            Spacer(Modifier.height(20.dp))
            ButtonEdit(

                onClick = {
                    OscClick("/4")
                    textoAntigo=stateScreen.ip
                    historicoEndereco.add(textoAntigo)
                },
                text ="DisparoOsc",
                modifier = Modifier.offset(y = 30.dp),
                textColors = Color.Black,
                enabled = stateScreen.connectionVerify
            )
            Spacer(Modifier.height(70.dp))

            historicoEndereco.forEach { textoAtual->
                textoAntigo=textoAtual
            }

            Text("Ultimo IP enviado: ${textoAntigo}")

            }







        }

    }



@Preview(showBackground = true, widthDp = 1340, heightDp = 800)
@Composable
fun GreetingPreview() {
    MainScreen(Conectar = {}, TODO(),{},{},{})
}


