package com.codingbot.shop.ui.screens.recommend

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.core.common.imageLocalMapperTmpEvent
import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.ui.component.clickableSingle
import com.codingbot.shop.ui.theme.CustomTheme
import com.codingbot.shop.viewmodel.EventDescViewModel
import com.codingbot.shop.viewmodel.EventSubViewModel


@Composable
fun EventListSubScreen(
    navController: NavController,
    id: Int,

    context: Context = LocalContext.current,
    eventSubViewModel: EventSubViewModel = hiltViewModel(),
) {
    val logger = remember { Logger("FavoriteScreen", true, "[Screen]") }

    val uiState = eventSubViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        eventSubViewModel.getEventData(id)
    }
//    Image(
//        painter = painterResource(id = productImg),
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(250.dp)
////                .aspectRatio(1f)
////                .padding(bottom = 10.dp),
//        ,
//        contentScale = ContentScale.Crop,
//        contentDescription = null
//    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustomTheme.colors.bg),
        //            horizontalAlignment = Alignment.CenterHorizontally,
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


@Composable
private fun EventCell(
    data: EventData,
    onClickEvent: (EventData) -> Unit,
    context: Context = LocalContext.current
)  {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .clickableSingle {
            onClickEvent(data)
        })
    {
        AsyncImage(
            model = ImageRequest
                .Builder(context)
                .data(imageLocalMapperTmpEvent(data.eventImg))
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 10.dp),
            contentScale = ContentScale.Crop,
        )

        Column {
            Text(
                text = data.eventName
            )

        }
    }
}