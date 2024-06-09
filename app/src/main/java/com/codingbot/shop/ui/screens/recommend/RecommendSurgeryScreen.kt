package com.codingbot.shop.ui.screens.recommend

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codingbot.shop.R
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.ui.component.DetailHeader
import com.codingbot.shop.ui.component.RecommendMenu
import com.codingbot.shop.ui.component.TabItem
import com.codingbot.shop.ui.component.TabsContent
import com.codingbot.shop.ui.theme.CustomTheme
import com.codingbot.shop.viewmodel.RecommendSurgeryViewModel
import kotlinx.coroutines.launch


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
