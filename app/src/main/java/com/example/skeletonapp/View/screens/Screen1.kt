package com.example.skeletonapp.View.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skeletonapp.View.widgets.ButtonEdit


// Arquivo responsável pelo design das telas e visualização do sistema


@Composable
fun MainScreen(onConect: (ip: String, porta: Int) -> Unit) {

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Blue)) {
        var ip by rememberSaveable { mutableStateOf("") }
        var porta by rememberSaveable { mutableStateOf("") }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = ip,
                onValueChange = { ip = it },
                label = { Text("Digite o IP aqui: ") })
            OutlinedTextField(
                value = porta,
                onValueChange = { porta = it },
                label = { Text("Digite a Porta aqui: ") })
            ButtonEdit(
                onClick = {
                    val portInt = porta.toIntOrNull()
                    if (portInt != null) onConect(ip, portInt)
                },
                text = "Ajuda",
                modifier = Modifier.offset(y = 30.dp),
                textColors = Color.Black
            )


        }

    }
}


@Preview(showBackground = true, widthDp = 1340, heightDp = 800)
@Composable
fun GreetingPreview() {
    MainScreen(onConect = { _, _ -> })
}


