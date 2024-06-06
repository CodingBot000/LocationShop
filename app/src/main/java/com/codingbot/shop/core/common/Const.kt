package com.codingbot.shop.core.common

import androidx.navigation.NavType
import androidx.navigation.navArgument

object Const {
    var sortingSpeed = 500f
    var sortingSpeedSliderInit = sortingSpeed / 100
    var globalMultipleInputTime = 0L

    const val GRAPH_HEIGHT_FROM = 10
    const val GRAPH_HEIGHT_TO = 150
    const val ARRAYS_SIZE = 13

    const val GRAPH_ARRAY_SIZE = 5
}


enum class MenuCategoriesName(val value: Int) {
    LOCATION(96), EVENT(97), FAVORITE(98), ABOUT_US(99)
}
enum class MenuListSection {
    MENU_CLICKABLE, MENU_NOCLICKABLE
}
sealed interface Screen {
    val route: String


    object SplashScreen : Screen {
        override val route: String = ScreenRoutes.SplashScreen
    }
    object MenuScreen : Screen {
        override val route: String = ScreenRoutes.MenuScreen
    }
    object MainScreen : Screen {
        override val route: String = ScreenRoutes.MainScreen
    }

    object DetailScreen : Screen {
        override val route: String = ScreenRoutes.DetailScreen

        const val id = "id"
//        const val searchQuery = "searchQuery"
//        const val productName = "productName"
//        const val productImgs = "productImgs"
//        const val productImgs2 = "productImgs2"
//        const val productImgs3 = "productImgs3"
//        const val productImgs4 = "productImgs4"
        val routeWithArgs = "$route/{$id}"
//        val routeWithArgs = "$route/{$id}/{$productName}/{$productImgs}/{$searchQuery}"
//    val routeWithArgs = "$route/{$id}/{$productName}/{$productImgs}/{$productImgs2}/{$productImgs3}/{$productImgs4}/{$searchQuery}"
        val arguments = listOf(
            navArgument(id) { type = NavType.IntType },
//            navArgument(productName) { type = NavType.StringType },
//            navArgument(productImgs) { type = NavType.StringType },
//            navArgument(productImgs2) { type = NavType.StringType },
//            navArgument(productImgs3) { type = NavType.StringType },
//            navArgument(productImgs4) { type = NavType.StringType },
//            navArgument(searchQuery) { type = NavType.StringType }

        )

        fun route(id: Int,
//                  productName: String,
//                  productImgs: String,
//                  searchQuery: String,
        ): String {
//            val productImgs = productImgsArray[0]
//            val productImgs2 =  try { productImgsArray[1] } catch (e: Exception) { "" }
//            val productImgs3 =  try { productImgsArray[2] } catch (e: Exception) { "" }
//            val productImgs4 =  try { productImgsArray[3] } catch (e: Exception) { "" }
//            return "${route}/$id/$productName/$productImgs/$searchQuery"
            return "${route}/$id"
//            return "${route}/$id/$productName/$productImgs/$productImgs2/$productImgs3/$productImgs4/$searchQuery"
        }
    }

    object RecommendSurgeryScreen : Screen {
        override val route: String = ScreenRoutes.RecommendSurgeryScreen

        const val id = "id"
        const val productName = "productName"
        const val productImg = "productImg"

        val routeWithArgs = "$route/{$id}/{$productName}/{$productImg}"
        val arguments = listOf(
            navArgument(id) { type = NavType.IntType },
            navArgument(productName) { type = NavType.StringType },
            navArgument(productImg) { type = NavType.IntType },

        )

        fun route(id: Int,
                  productName: String,
                  productImg: Int,
        ): String {
            return "${route}/$id/$productName/$productImg"
        }
    }
    object TreatmentDetailDescScreen : Screen {
        override val route: String = ScreenRoutes.TreatmentDetailDescScreen

        const val id = "id"

        val routeWithArgs = "$route/{$id}"
        val arguments = listOf(
            navArgument(id) { type = NavType.IntType },

        )

        fun route(
            id: Int
        ): String {
            return "${route}/$id"
        }
    }

    object LocationScreen : Screen {
        override val route: String = ScreenRoutes.LocationScreen

        const val location = "location"

        val routeWithArgs = "$route/{$location}"
        val arguments = listOf(
            navArgument(location) { type = NavType.StringType },

            )

        fun route(
            location: String
        ): String {
            return "${route}/$location"
        }
    }

    object EventListSubScreen : Screen {
        override val route: String = ScreenRoutes.EventListSubScreen

        const val id = "id"

        val routeWithArgs = "$route/{$id}"
        val arguments = listOf(
            navArgument(id) { type = NavType.IntType },
            )

        fun route(id: Int,
        ): String {
            return "${route}/$id"
        }
    }

    object ReviewListSubScreen : Screen {
        override val route: String = ScreenRoutes.ReviewListSubScreen

        const val id = "id"

        val routeWithArgs = "$route/{$id}"
        val arguments = listOf(
            navArgument(id) { type = NavType.IntType },
        )

        fun route(id: Int,
        ): String {
            return "${route}/$id"
        }
    }

    object HospitalListSubScreen : Screen {
        override val route: String = ScreenRoutes.HospitalListSubScreen

        const val id = "id"

        val routeWithArgs = "$route/{$id}"
        val arguments = listOf(
            navArgument(id) { type = NavType.IntType },
        )

        fun route(id: Int,
        ): String {
            return "${route}/$id"
        }
    }
    object EventDescScreen : Screen {
        override val route: String = ScreenRoutes.EventDescScreen

        const val id = "id"

        val routeWithArgs = "$route/{$id}"
        val arguments = listOf(
            navArgument(id) { type = NavType.IntType },
        )

        fun route(id: Int,
        ): String {
            return "${route}/$id"
        }
    }
    object FavoriteScreen : Screen {
        override val route: String = ScreenRoutes.FavoriteScreen
    }

    object AboutUsScreen : Screen {
        override val route: String = ScreenRoutes.AboutUsScreen
    }

    object EventMenuScreen : Screen {
        override val route: String = ScreenRoutes.EventMenuScreen
    }



}



private object ScreenRoutes {
    const val SplashScreen = "SplashScreen"
    const val MenuScreen = "MenuScreen"
    const val MainScreen =  "MainScreen"
    const val DetailScreen = "DetailScreen"
    const val RecommendSurgeryScreen = "RecommendSurgeryScreen"
    const val TreatmentDetailDescScreen = "TreatmentDetailDescScreen"
    const val LocationScreen = "LocationScreen"
    const val FavoriteScreen = "FavoriteScreen"
    const val EventListSubScreen = "EventListSubScreen"
    const val ReviewListSubScreen = "ReviewListSubScreen"
    const val HospitalListSubScreen = "HospitalListSubScreen"
    const val EventDescScreen = "EventDescScreen"
    const val AboutUsScreen = "AboutUsScreen"
    const val EventMenuScreen = "EventMenuScreen"
}

