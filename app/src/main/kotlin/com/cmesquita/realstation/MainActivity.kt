package com.cmesquita.realstation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.cmesquita.realstation.ui.details.navigation.navigateToRealStateDetails
import com.cmesquita.realstation.ui.details.navigation.realStateDetailsScreen
import com.cmesquita.realstation.ui.list.navigation.realStateListRoute
import com.cmesquita.realstation.ui.list.navigation.realStateListScreen
import com.cmesquita.realstation.ui.theme.RealStationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            RealStationTheme {
                NavHost(
                    navController = navController,
                    enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
                    exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
                    popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right) },
                    popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right) },
                    startDestination = realStateListRoute,
                ) {
                    realStateListScreen(
                        navigateToDetails = { id ->
                            navController.navigateToRealStateDetails(id)
                        }
                    )
                    realStateDetailsScreen()
                }
            }
        }
    }
}
