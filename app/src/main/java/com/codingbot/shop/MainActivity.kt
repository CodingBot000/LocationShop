package com.codingbot.shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.core.server.DumpServer
import com.codingbot.shop.ui.screens.main.MainScreen
import com.codingbot.shop.ui.screens.SplashScreen
import com.codingbot.shop.ui.screens.aboutus.AboutUsScreen
import com.codingbot.shop.ui.screens.detail.DetailScreen
import com.codingbot.shop.ui.screens.event.EventDescScreen
import com.codingbot.shop.ui.screens.event.EventMenuScreen
import com.codingbot.shop.ui.screens.favorite.FavoriteScreen
import com.codingbot.shop.ui.screens.location.LocationScreen
import com.codingbot.shop.ui.screens.main.HospitalListByRegionScreen
import com.codingbot.shop.ui.screens.menu.MenuScreen
import com.codingbot.shop.ui.screens.menu.TreatmentDetailDescScreen
import com.codingbot.shop.ui.screens.recommend.EventListSubScreen
import com.codingbot.shop.ui.screens.recommend.HospitalListSubScreen
import com.codingbot.shop.ui.screens.recommend.RecommendSurgeryScreen
import com.codingbot.shop.ui.screens.recommend.ReviewListSubScreen
import com.codingbot.shop.ui.theme.LocationShopTheme
import dagger.hilt.android.AndroidEntryPoint

val LocalRootNavHost =
    staticCompositionLocalOf<NavHostController>{ error("Nav host controller is not provided") }


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DumpServer.init()

        setContent {
            LocationShopTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainScreen.route
                    ) {
                        composable(route = Screen.SplashScreen.route) {
                            SplashScreen(navController = navController)
                        }
                        composable(
                            route = Screen.MenuScreen.route,
                            enterTransition = { slideInHorizontally(animationSpec = tween(500)) },
                            exitTransition = { slideOutHorizontally(animationSpec = tween(500)) },
                            popEnterTransition =  { slideInHorizontally(animationSpec = tween(500)) },
                            popExitTransition = { slideOutHorizontally(animationSpec = tween(500)) }
                        )
                        {
                            MenuScreen(navController = navController)
                        }
                        composable(
                            route = Screen.MainScreen.route)
                        {
                            MainScreen(navController = navController)
                        }
                        composable(
                            route = Screen.DetailScreen.routeWithArgs,
                            arguments = Screen.DetailScreen.arguments
                            ) { entry ->
                                val id = entry.arguments?.getInt(Screen.DetailScreen.id) ?: 1
                                DetailScreen(
                                    navController = navController,
                                    id = id,
                                )
                            }

                        composable(
                            route = Screen.RecommendSurgeryScreen.routeWithArgs,
                            arguments = Screen.RecommendSurgeryScreen.arguments
                        ) { entry ->
                            val id = entry.arguments?.getInt(Screen.RecommendSurgeryScreen.id) ?: 1
                            val productName = entry.arguments?.getString(Screen.RecommendSurgeryScreen.productName) ?: ""
                            val productImg = entry.arguments?.getInt(Screen.RecommendSurgeryScreen.productImg) ?: R.drawable.surgery_acne
                            RecommendSurgeryScreen(
                                navController = navController,
                                id = id,
                                productName = productName,
                                productImg = productImg
                            )
                        }

                        composable(
                            route = Screen.TreatmentDetailDescScreen.routeWithArgs,
                            arguments = Screen.TreatmentDetailDescScreen.arguments
                        ) { entry ->
                            val id = entry.arguments?.getInt(Screen.TreatmentDetailDescScreen.id) ?: 1
                            TreatmentDetailDescScreen(
                                navController = navController,
                                id = id
                            )
                        }

                        composable(
                            route = Screen.LocationScreen.routeWithArgs,
                            arguments = Screen.LocationScreen.arguments
                        ) { entry ->
                            val locationNameString = entry.arguments?.getString(Screen.LocationScreen.location) ?: ""
                            LocationScreen(
                                navController = navController,
                                locationNameString = locationNameString
                            )
                        }

                        composable(
                            route = Screen.RecommendSurgeryScreen.routeWithArgs,
                            arguments = Screen.RecommendSurgeryScreen.arguments
                        ) { entry ->
                            val id = entry.arguments?.getInt(Screen.RecommendSurgeryScreen.id) ?: 1
                            val productName = entry.arguments?.getString(Screen.RecommendSurgeryScreen.productName) ?: ""
                            val productImg = entry.arguments?.getInt(Screen.RecommendSurgeryScreen.productImg) ?: R.drawable.surgery_acne
                            RecommendSurgeryScreen(
                                navController = navController,
                                id = id,
                                productName = productName,
                                productImg = productImg
                            )
                        }

                        composable(
                            route = Screen.HospitalListByRegionScreen.routeWithArgs,
                            arguments = Screen.HospitalListByRegionScreen.arguments
                        ) { entry ->
                            val region = entry.arguments?.getString(Screen.HospitalListByRegionScreen.region) ?: ""
                            HospitalListByRegionScreen(
                                navController = navController,
                                region = region
                            )
                        }

                        composable(
                            route = Screen.FavoriteScreen.route)
                        {
                            FavoriteScreen(navController = navController)
                        }

                        composable(
                            route = Screen.EventListSubScreen.routeWithArgs,
                            arguments = Screen.EventListSubScreen.arguments
                        ) { entry ->
                            val id = entry.arguments?.getInt(Screen.EventListSubScreen.id) ?: 1
                            EventListSubScreen(
                                navController = navController,
                                id = id,
                            )
                        }

                        composable(
                            route = Screen.ReviewListSubScreen.routeWithArgs,
                            arguments = Screen.ReviewListSubScreen.arguments
                        ) { entry ->
                            val id = entry.arguments?.getInt(Screen.ReviewListSubScreen.id) ?: 1
                            ReviewListSubScreen(
                                navController = navController,
                                id = id,
                            )
                        }


                        composable(
                            route = Screen.HospitalListSubScreen.routeWithArgs,
                            arguments = Screen.HospitalListSubScreen.arguments
                        ) { entry ->
                            val id = entry.arguments?.getInt(Screen.HospitalListSubScreen.id) ?: 1
                            HospitalListSubScreen(
                                navController = navController,
                                id = id,
                            )
                        }
                        composable(
                            route = Screen.EventListSubScreen.routeWithArgs,
                            arguments = Screen.EventListSubScreen.arguments
                        ) { entry ->
                            val id = entry.arguments?.getInt(Screen.EventListSubScreen.id) ?: 1
                            EventListSubScreen(
                                navController = navController,
                                id = id,
                            )
                        }


                        composable(
                            route = Screen.EventMenuScreen.route)
                        {
                            EventMenuScreen(navController = navController)
                        }

                        composable(
                            route = Screen.AboutUsScreen.route)
                        {
                            AboutUsScreen(navController = navController)
                        }

                        composable(
                            route = Screen.EventDescScreen.routeWithArgs,
                            arguments = Screen.EventDescScreen.arguments
                        ) { entry ->
                            val id = entry.arguments?.getInt(Screen.EventDescScreen.id) ?: 1
                            EventDescScreen(
                                navController = navController,
                                id = id,
                            )
                        }
                    }
                }
            }
        }
    }
}
