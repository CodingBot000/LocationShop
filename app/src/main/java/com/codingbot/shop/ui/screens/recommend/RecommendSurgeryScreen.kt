package com.codingbot.shop.ui.screens.recommend

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.ui.component.DetailHeader
import com.codingbot.shop.ui.component.RecommendMenu
import com.codingbot.shop.ui.component.TabItem
import com.codingbot.shop.ui.component.TabsContent
import com.codingbot.shop.ui.theme.CustomTheme
import com.codingbot.shop.viewmodel.RecommendSurgeryViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecommendSurgeryScreen(
    navController: NavController,
    id: Int,
    recommendSurgeryViewModel: RecommendSurgeryViewModel = hiltViewModel(),
) {
    val logger = remember { Logger("RecommendSurgeryViewModel", true, "[Screen]") }

    val uiState = recommendSurgeryViewModel.uiState.collectAsStateWithLifecycle()
    val tabs = remember { listOf(
        TabItem.Event(fromScreen = Screen.RecommendSurgeryScreen, id = id, navController = navController),
        TabItem.Reviews(fromScreen = Screen.RecommendSurgeryScreen, id = id, navController = navController),
        TabItem.Hospitals(fromScreen = Screen.RecommendSurgeryScreen, id = id, navController = navController)
    ) }
    val pagerState = rememberPagerState(pageCount = {
        tabs.size
    })

    LaunchedEffect(key1 = Unit) {
        recommendSurgeryViewModel.getEventData(id)
    }

    Column(
        modifier = Modifier
            .background(color = CustomTheme.colors.bg)
            .fillMaxSize(),
    )
    {
        DetailHeader(
            title = "Recommend",
            onClickBack = {
                navController.popBackStack()
            },
        )

        RecommendMenu(
            tabs = tabs,
            pagerState = pagerState,
            onClickItem = {selectedItem ->

            }
        )
        TabsContent(tabs = tabs, pagerState = pagerState)
    }
}
