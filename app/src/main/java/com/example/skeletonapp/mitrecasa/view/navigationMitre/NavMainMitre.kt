package com.example.skeletonapp.mitrecasa.view.navigationMitre

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.skeletonapp.mitrecasa.view.MainScreenMitre
import com.example.skeletonapp.mitrecasa.viewmodel.Screen1ViewModelMitre


@Composable
fun AppNavigationMitre() {
    val backStack = rememberNavBackStack(ScreenKey1Mitre)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {

            entry<ScreenKey1Mitre> {
                val viewModel: Screen1ViewModelMitre = hiltViewModel()
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                MainScreenMitre(
                    stateScreen = state,
                    ipChange = viewModel::IpChange,
                    portChange = viewModel::PortChange,
                    oscClick = viewModel::OscClick


                )
            }
        })
}







