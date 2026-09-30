package com.example.skeletonapp.View.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.skeletonapp.View.screens.MainScreen
import com.example.skeletonapp.View.screens.MainScreen2


//Arquivo responsável pela lógica das navegações de tela.


@Composable
fun AppNavigation() {
    val backStack = rememberNavBackStack(screen1)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {

            //TELA 1
            entry<screen1> {
                MainScreen(onConect = { ip, porta ->
                    backStack.add(
                        screen2(ip = ip, porta = porta)
                    )
                })
            }
            //TELA 2
            entry<screen2> { key ->
                MainScreen2(
                    ip = key.ip,
                    porta = key.porta,
                    onVoltar = { backStack.removeLastOrNull() })
            }

        }


    )

}








