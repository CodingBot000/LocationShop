package com.codingbot.shop.ui.screens.event

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codingbot.shop.R
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.ui.component.DetailHeader
import com.codingbot.shop.ui.component.clickableSingle
import com.codingbot.shop.ui.theme.Color
import com.codingbot.shop.ui.theme.CustomTheme
import com.codingbot.shop.viewmodel.EventDescViewModel


@Composable
fun EventDescScreen(
    navController: NavController,
    id: Int,
    context: Context = LocalContext.current,
    eventDescViewModel: EventDescViewModel = hiltViewModel(),
) {
    val logger = remember { Logger("EventDescScreen", true, "[Screen]") }
    val uiState = eventDescViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        eventDescViewModel.getEventData(id)
    }
    Column(
        modifier = Modifier
            .background(color = CustomTheme.colors.bg)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        DetailHeader(
            title = "Event",
            onClickBack = {
                navController.popBackStack()
            }
        )

        Column(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .verticalScroll(rememberScrollState()),
        )
        {
            uiState.value.detailData?.let {
                AsyncImage(
                    model = ImageRequest
                        .Builder(context)
                        .data(it.eventImg)
                        .placeholder(R.drawable.default_beauty_surgery)
                        .build(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .padding(bottom = 10.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            ) {
                uiState.value.detailData?.let { eventData ->
                    Text(
                        text = eventData.eventName,
                        style = CustomTheme.typography.title1Bold,
                        textAlign = TextAlign.Center)
                }

                uiState.value.detailData?.let { eventData ->
                    Text(
                        text = "${eventData.eventDateFrom} ~ ${eventData.eventDateTo}",
                        style = CustomTheme.typography.title3Regular,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                uiState.value.detailData?.let { eventData ->
                    SurgeryList(
                        surgeryNamelist = eventDescViewModel.getSurgeryNames(eventData.surgeryIds)
                        )
                }
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                uiState.value.detailData?.let { eventData->
                    Text(text = eventData.desc)
                }

                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Gray_20))
                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                uiState.value.productData?.let { info->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clickableSingle {
                            navController.navigate(Screen.DetailScreen.route(info.id))
                        })
                    {
                        if (!info.images.isNullOrEmpty()) {
                            AsyncImage(
                                model = ImageRequest
                                    .Builder(context)
                                    .data(info.images[0])
                                    .build(),
                                contentDescription = "hospitalImg",
                                modifier = Modifier
                                    .width(180.dp)
                                    .aspectRatio(1f)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .padding(horizontal = 10.dp),
                                contentScale = ContentScale.Crop,
                                error = painterResource(R.drawable.load_waiting)
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Column {
                            Text(
                                text = "Hospital",
                                style = CustomTheme.typography.bodyRegular,
                            )
                            Text(
                                text = "      ${info.productName}",
                                style = CustomTheme.typography.title3Bold,
                            )
                        }

                    }
                }
            }
        }
    }
}



@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SurgeryList(
    surgeryNamelist: List<String>,
)
{
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(CustomTheme.colors.infoBox)
        .padding(5.dp)
        .clip(shape = RoundedCornerShape(15.dp))
    ) {
        Text(text = "Surgeries Package",
            color = CustomTheme.colors.textColorPrimary,
            style = CustomTheme.typography.title3Bold)
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            surgeryNamelist.forEach { data ->
                NameFilterChipContent(
                    surgeryNamelist = data,
                )
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
private fun NameFilterChipContent(
    surgeryNamelist: String,
) {
    Chip(
        shape = RoundedCornerShape(50.dp),
        colors = ChipDefaults.chipColors(
            backgroundColor = CustomTheme.colors.white,
            contentColor = androidx.compose.ui.graphics.Color.Black),
        border = BorderStroke(1.dp, CustomTheme.colors.black),
        onClick = { /*TODO*/ },
        content = {
            Text(text = surgeryNamelist,
                color = CustomTheme.colors.textColorPrimary,
                style = CustomTheme.typography.bodyRegular,)
        })

}