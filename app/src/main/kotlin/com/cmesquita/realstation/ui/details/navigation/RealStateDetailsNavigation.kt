package com.cmesquita.realstation.ui.details.navigation

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

fun NavGraphBuilder.realStateDetailsScreen() {
    composable(
        route = realStateDetailsRoute,
        arguments = listOf(
            navArgument(realStateIdArg) { type = NavType.StringType },
        ),
    ) {
        RealStateDetailsScreen()
    }
}
