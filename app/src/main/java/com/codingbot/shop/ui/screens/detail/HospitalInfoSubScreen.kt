package com.codingbot.shop.ui.screens.detail

import MapView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.core.common.imageLocalMapperTmpDoctors
import com.codingbot.shop.ui.component.TabItem
import com.codingbot.shop.ui.theme.CustomTheme
import com.codingbot.shop.viewmodel.HospitalInfoSubViewModel


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HospitalInfoSubScreen(
    navController: NavController,
    id: Int,
    hospitalInfoSubViewModel: HospitalInfoSubViewModel = hiltViewModel(),
) {
    val logger = remember { Logger("HospitalInfoSubScreen", true, "[Screen]") }

    val PREV_SPACE = "    "
    val uiState = hospitalInfoSubViewModel.uiState.collectAsStateWithLifecycle()
    val tabs = remember { listOf(
        TabItem.Event(fromScreen = Screen.HospitalListSubScreen, id = id, navController = navController),
        TabItem.Reviews(fromScreen = Screen.HospitalListSubScreen, id = id, navController = navController),
        TabItem.Hospitals(fromScreen = Screen.HospitalListSubScreen, id = id, navController = navController)
    ) }
    val pagerState = rememberPagerState(pageCount = {
        tabs.size
    })

    LaunchedEffect(key1 = Unit) {
        hospitalInfoSubViewModel.getDetailData(id)
    }
    if (uiState.value.detailData == null) {
        return
    }
    Box(
        modifier = Modifier
            .background(color = CustomTheme.colors.bg)
            .fillMaxSize(),
    )
    {
        Column(
            modifier = Modifier
    //            .background(color = CustomTheme.colors.bg)
                .fillMaxSize(),
        )
        {
            Box(modifier = Modifier.fillMaxSize()
                )
            {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = CustomTheme.colors.bg),
                    //            horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    item {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp, start = 10.dp, end = 10.dp),
                            text = "Address",
                            color = CustomTheme.colors.black,
                            style = CustomTheme.typography.bodyBold,

                            )
                    }


                    item {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp),
                            text = PREV_SPACE + uiState.value.detailData?.detailDesc?.descAddress ?: "",
                            color = CustomTheme.colors.black,
                            style = CustomTheme.typography.captionRegular,

                            )
                    }


                    item {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp, start = 10.dp, end = 10.dp),
                            text = "Opening Hour",
                            color = CustomTheme.colors.black,
                            style = CustomTheme.typography.bodyBold,

                            )
                    }


                    item {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp),
                            text = PREV_SPACE + uiState.value.detailData?.detailDesc?.openingHour?.trim() ?: "",
                            color = CustomTheme.colors.black,
                            style = CustomTheme.typography.captionRegular,

                            )
                    }

                    item {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp, start = 10.dp, end = 10.dp),
                            text = "Facilities",
                            color = CustomTheme.colors.black,
                            style = CustomTheme.typography.bodyBold,

                            )
                    }


                    item {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp),
                            text = PREV_SPACE + uiState.value.detailData?.detailDesc?.facilities ?: "",
                            color = CustomTheme.colors.black,
                            style = CustomTheme.typography.captionRegular,

                            )
                    }

                    item {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp, start = 10.dp, end = 10.dp),
                            text = "Doctors",
                            color = CustomTheme.colors.black,
                            style = CustomTheme.typography.bodyBold,

                            )
                    }

                    item {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp))
                        {
                            uiState.value.detailData?.detailDesc?.doctors?.let { doctors ->
                                doctors.forEach { dotcorFileName ->
                                    Image(
                                        painter = painterResource(imageLocalMapperTmpDoctors(dotcorFileName)),
                                        contentDescription = "doctor",
                                        contentScale = ContentScale.Crop,            // crop the image if it's not a square
                                        modifier = Modifier
                                            .size(64.dp)
                                            .clip(CircleShape)                       // clip to the circle shape
                                            .border(
                                                2.dp,
                                                CustomTheme.colors.bgOpposite,
                                                CircleShape
                                            )   // add a border (optional)
                                    )
                                    Spacer(modifier = Modifier.padding(end = 10.dp))

                                }
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.padding(bottom = 10.dp))
                    }
                    item {
                        uiState.value.detailData?.let {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                                    .clip(shape = RoundedCornerShape(15.dp))
                                    .padding(horizontal = 10.dp)
                            ) {
                                uiState.value.productData?.let { productData ->
                                    MapView(arrayListOf(productData.searchQuery))
                                }

                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.padding(end = 10.dp))
                    }
                } // End of Lazy
            } // end of Box
        }
    }
}



