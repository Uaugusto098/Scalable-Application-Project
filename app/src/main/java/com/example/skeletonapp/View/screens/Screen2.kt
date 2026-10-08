package com.example.skeletonapp.View.screens

import android.widget.Toast
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skeletonapp.View.widgets.ButtonEdit
import com.example.skeletonapp.ViewModel.ViewScreen2


@Composable
fun Screen2(stateScreen2: ViewScreen2, OscClick: (String) -> Unit, onVoltar: () -> Unit) {

    val context=LocalContext.current
    LaunchedEffect(stateScreen2.erro){
        stateScreen2.erro?.let { mensagem->
            if(mensagem.isNotEmpty()){Toast.makeText(context,mensagem, Toast.LENGTH_LONG).show()}
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
       var lastClick by remember { mutableStateOf(0L) }


        Box(
            modifier = Modifier
                .wrapContentSize(align = Alignment.Center)
                .background(Color(0xFF555934))

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Spacer(Modifier.height(20.dp))
                ButtonEdit(
                    onClick = { OscClick("1")
                            },
                    text = "Cena 1",
                    Modifier
                        .width(100.dp)
                        .height(50.dp),
                    textColors = Color.Black,
                    fontSize = 15.sp,
                    enabled = stateScreen2.connectionVerify
                )
                Spacer(Modifier.height(20.dp))
                ButtonEdit(
                    onClick = {
                        OscClick("2")
                    },
                    text = "Cena 2 ",
                    Modifier
                        .width(100.dp)
                        .height(50.dp),
                    textColors = Color.Black,
                    fontSize = 15.sp,
                    enabled = stateScreen2.connectionVerify

                )

                Spacer(Modifier.height(20.dp))
                ButtonEdit(
                    onClick = {
                        val time=System.currentTimeMillis()
                        if(time-lastClick>=2000){
                            lastClick= time
                            onVoltar()
                        }

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





