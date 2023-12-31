package com.cmesquita.realstation.ui.list.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cmesquita.realstation.ui.list.RealStateListScreen

const val realStateListRoute = "list"

fun NavGraphBuilder.realStateListScreen(
    navigateToDetails: (String) -> Unit,
) {
    composable(route = realStateListRoute) {
        RealStateListScreen(
            viewModel = hiltViewModel(),
            navigateToDetails = navigateToDetails
        )
    }
}
