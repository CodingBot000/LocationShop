package com.codingbot.shop.ui.screens.recommend

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.ui.component.EventCell
import com.codingbot.shop.ui.theme.CustomTheme
import com.codingbot.shop.viewmodel.EventSubViewModel


@Composable
fun EventListSubScreen(
    navController: NavController,
    id: Int,
    eventSubViewModel: EventSubViewModel = hiltViewModel(),
) {
    val logger = remember { Logger("EventListSubScreen", true, "[Screen]") }

    val uiState = eventSubViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        eventSubViewModel.getEventData(id)
    }

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
                onClickEvent = {data ->
                    navController.navigate(Screen.EventDescScreen.route(data.id))
                })

        }
    } // End of Lazy
}
