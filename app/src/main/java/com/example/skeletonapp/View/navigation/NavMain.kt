package com.example.skeletonapp.View.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.skeletonapp.Model.osc.OscSender
import com.example.skeletonapp.Model.osc.UdpOscSender
import com.example.skeletonapp.Model.repository.MainRepository
import com.example.skeletonapp.View.screens.MainScreen
import com.example.skeletonapp.View.screens.Screen2
import com.example.skeletonapp.ViewModel.Screen1ViewModel
import com.example.skeletonapp.ViewModel.Screen2ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


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
                val viewModel: Screen1ViewModel = hiltViewModel()
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                MainScreen(
                    Conectar = { ip, port -> backStack.add(ScreenKey2(ip = ip, port = port)) },
                    stateScreen = state,
                    IpChange = viewModel::IpChange,
                    PortChange = viewModel::PortChange,
                    OscClick = viewModel::OscClickScreen1


                )
            }
            //TELA 2
            entry<ScreenKey2> { key ->
                val viewModel = hiltViewModel<Screen2ViewModel, Screen2ViewModel.Factory>(
                    creationCallback = { factory -> factory.create(key.ip, key.port) })

                val state by viewModel.uiState.collectAsStateWithLifecycle()
                Screen2(
                    state = state,
                    onVoltar = { if(backStack.lastOrNull()==key)backStack.removeLastOrNull() },
                    OscClick = viewModel::OscClickScreen2

                )
            }

        }


    )

}








