package com.codingbot.shop.ui.screens.event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.ui.component.DetailHeader
import com.codingbot.shop.ui.component.EventCell
import com.codingbot.shop.ui.theme.CustomTheme
import com.codingbot.shop.viewmodel.EventMenuViewModel


@Composable
fun EventMenuScreen(
    navController: NavController,
    eventMenuViewModel: EventMenuViewModel = hiltViewModel(),
) {
    val logger = remember { Logger("FavoriteScreen", true, "[Screen]") }
    val uiState = eventMenuViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        eventMenuViewModel.getAllEventData()
    }
    Column(
        modifier = Modifier
            .background(color = CustomTheme.colors.bg)
            .fillMaxSize(),
    )
    {
        DetailHeader(
            title = "All Events",
            onClickBack = {
                navController.popBackStack()
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = CustomTheme.colors.bg),
        )
        {
            item {
                Spacer(modifier = Modifier.padding(top = 10.dp))
            }

            items(uiState.value.eventDataList?.size ?: 0) { index ->
                val data = uiState.value.eventDataList!![index]
                EventCell(
                    data = data,
                    onClickEvent = { data ->
                        navController.navigate(Screen.EventDescScreen.route(data.id))
                    })

            }
        } // End of Lazy
    }
}

