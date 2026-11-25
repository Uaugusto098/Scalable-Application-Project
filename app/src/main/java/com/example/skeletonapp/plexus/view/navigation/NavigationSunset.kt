package com.example.skeletonapp.plexus.view.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay


@Composable
fun AppNavigationPlexus(){

    val backStack= rememberNavBackStack(Screen1Plexus)

    NavDisplay(
        backStack=backStack,
        onBack = {backStack.removeLastOrNull()},
        entryDecorators =listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            ),
        entryProvider = entryProvider {
            entry<Screen1Plexus>{

            }
        }

    )


}