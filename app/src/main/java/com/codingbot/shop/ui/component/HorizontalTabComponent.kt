package com.codingbot.shop.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codingbot.shop.R
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.ui.screens.detail.HospitalInfoSubScreen
import com.codingbot.shop.ui.screens.recommend.EventListSubScreen
import com.codingbot.shop.ui.screens.recommend.HospitalListSubScreen
import com.codingbot.shop.ui.screens.recommend.ReviewListSubScreen
import com.codingbot.shop.ui.theme.CustomTheme
import kotlinx.coroutines.launch


typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(val fromScreen: Screen, val id: Int, val navController: NavController, var icon: Int, var title: String, var screen: ComposableFun) {
    class HospitalInfo(fromScreen: Screen, id: Int, navController: NavController) : TabItem(fromScreen, id, navController, R.drawable.sns_youtube, "Info", { HospitalInfoSubScreen(navController = navController, id = id) })
    class Event(fromScreen: Screen, id: Int, navController: NavController) : TabItem(fromScreen, id, navController, R.drawable.sns_youtube, "Event", { EventListSubScreen(navController = navController, id = id) })
    class Reviews(fromScreen: Screen, id: Int, navController: NavController) : TabItem(fromScreen, id, navController, R.drawable.sns_homepage, "Reviews", { ReviewListSubScreen(navController = navController, id = id)  })
    class Hospitals(fromScreen: Screen, id: Int, navController: NavController) : TabItem(fromScreen, id, navController, R.drawable.sns_map, "Hospitals", { HospitalListSubScreen(navController = navController, id = id) })
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(
    tabs: List<TabItem>,
    pagerState: PagerState
) {
    HorizontalPager(state = pagerState) { page ->
        tabs[page].screen()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecommendMenu(
    tabs: List<TabItem>,
    pagerState: PagerState,
    onClickItem: (TabItem) -> Unit
) {

    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = CustomTheme.colors.white,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(32.dp),
        contentColor = CustomTheme.colors.black
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { Text(tab.title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                    onClickItem(tab)

                },
            )
        }
    }
}
