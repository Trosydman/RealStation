package com.cmesquita.realstation.ui.details.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cmesquita.realstation.ui.details.RealStateDetailsScreen

const val realStateIdArg = "realStateId"
const val realStateDetailsPath = "details"
const val realStateDetailsRoute = "$realStateDetailsPath/{$realStateIdArg}"

fun NavController.navigateToRealStateDetails(id: String) {
    navigate("$realStateDetailsPath/$id")
}

fun NavGraphBuilder.realStateDetailsScreen(onBackClick: () -> Unit) {
    composable(
        route = realStateDetailsRoute,
        arguments = listOf(
            navArgument(realStateIdArg) { type = NavType.StringType },
        ),
    ) {
        val realStateId = requireNotNull(
            it.arguments?.getString(realStateIdArg),
        ) { "\"$realStateIdArg\" parameter wasn't found. Please make sure it's set!" }

        RealStateDetailsScreen(
            viewModel = hiltViewModel(),
            realStateId = realStateId,
            onBackClick = onBackClick,
        )
    }
}
