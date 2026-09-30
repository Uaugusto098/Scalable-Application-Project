package com.example.skeletonapp.View.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skeletonapp.View.widgets.ButtonEdit


@Composable
fun MainScreen2(ip: String, porta: Int, onVoltar: () -> Unit) {

    Surface(modifier = Modifier.fillMaxSize()) {
        var portaString = porta.toString()

        Box(
            modifier = Modifier
                .wrapContentSize(align = Alignment.Center)
                .background(Color(0xFF555934))
                .width(250.dp)
                .height(100.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "$ip")
                Text(text = "$portaString")
                Spacer(Modifier.height(20.dp))
                ButtonEdit(
                    onClick = { onVoltar() },
                    text = "Voltar",
                    Modifier
                        .width(100.dp)
                        .height(50.dp),
                    textColors = Color.Black,
                    fontSize = 15.sp
                )
            }
        }


    }

}





