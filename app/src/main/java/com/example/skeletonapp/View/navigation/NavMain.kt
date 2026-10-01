package com.example.skeletonapp.View.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.skeletonapp.Model.osc.UdpOscSender
import com.example.skeletonapp.View.screens.MainScreen
import com.example.skeletonapp.View.screens.Screen2
import com.example.skeletonapp.ViewModel.OscViewModel
import com.example.skeletonapp.ViewModel.Screen1ViewModel


//Arquivo responsável pela lógica das navegações de tela.


@Composable
fun AppNavigation() {
    val backStack = rememberNavBackStack(ScreenKey1)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {

            //TELA 1
            entry<ScreenKey1> {
                val viewModel: Screen1ViewModel = viewModel()
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                MainScreen(
                    Conectar = { ip, porta -> backStack.add(ScreenKey2(ip = ip, porta = porta)) },
                    stateScreen = state,
                    IpChange = viewModel::IpChange,
                    PortChange = viewModel::PortChange,
                    //OscClick =


                )
            }
            //TELA 2
            entry<ScreenKey2> { key ->

                Screen2(
                    ip = key.ip,
                    porta = key.porta,
                    onVoltar = { backStack.removeLastOrNull() })
            }

        }


    )

}








