package com.example.skeletonapp.View.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skeletonapp.View.widgets.ButtonEdit
import com.example.skeletonapp.ViewModel.Screen2ViewModel
import com.example.skeletonapp.ViewModel.UiState.Screen1UiState


@Composable
fun Screen2(state: Screen1UiState, OscClick: (String) -> Unit, onVoltar: () -> Unit) {

    Surface(modifier = Modifier.fillMaxSize()) {
        val localContext = LocalContext.current

        Box(
            modifier = Modifier
                .wrapContentSize(align = Alignment.Center)
                .background(Color(0xFF555934))
                .width(1000.dp)
                .height(1000.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Spacer(Modifier.height(20.dp))
                ButtonEdit(
                    onClick = { OscClick("1") },
                    text = "Cena 1",
                    Modifier
                        .width(100.dp)
                        .height(50.dp),
                    textColors = Color.Black,
                    fontSize = 15.sp,
                    enabled = state.verificacaoConexao//resolver bug do enable, botao fica desativo mesmo com ip e porta, state.verificarconexao nao esta funcionando aq
                )
                Spacer(Modifier.height(20.dp))
                ButtonEdit(
                    onClick = { OscClick("2") },
                    text = "Cena 2 ",
                    Modifier
                        .width(100.dp)
                        .height(50.dp),
                    textColors = Color.Black,
                    fontSize = 15.sp,
                    enabled = state.verificacaoConexao

                )

                Spacer(Modifier.height(20.dp))
                ButtonEdit(
                    onClick = {
                        onVoltar()
                    },
                    text = "Voltar ",
                    Modifier
                        .width(100.dp)
                        .height(50.dp),
                    textColors = Color.Black,
                    fontSize = 15.sp,

                )
            }
        }


    }

}





