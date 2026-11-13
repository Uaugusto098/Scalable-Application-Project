package com.example.skeletonapp.naeem.View.navigationnaeem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.skeletonapp.naeem.View.MainScreenNaeem
import com.example.skeletonapp.naeem.ViewModel.Screen1ViewModelNaeem


@Composable
fun AppNavigationNaeem() {
    val backStack = rememberNavBackStack(Screen1KeyNaeem)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Screen1KeyNaeem> {
                val viewModel: Screen1ViewModelNaeem = hiltViewModel()
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                MainScreenNaeem(
                    stateScreen = state,
                    ipChange = viewModel::IpChange,
                    portChange = viewModel::PortChange,
                    oscClick = viewModel::OscClick
                )
            }
        }


    )


}