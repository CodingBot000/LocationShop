package com.codingbot.shop.ui.screens.main

import android.Manifest
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScrollModifierNode
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
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.core.common.imageLocalMapperTmpHospital
import com.codingbot.shop.core.utils.SpeechToText
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.ui.component.Grid2ItemsByRowCell
import com.codingbot.shop.ui.component.MainHeader
import com.codingbot.shop.ui.component.clickableSingle
import com.codingbot.shop.ui.theme.CustomTheme
import com.codingbot.shop.ui.theme.CustomTheme.colors
import com.codingbot.shop.viewmodel.MainIntent
import com.codingbot.shop.viewmodel.MainViewModel

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel  = hiltViewModel(),
    context: Context = LocalContext.current
) {
    val logger = remember { Logger("MainScreen", true, "[Screen]") }

    val uiState = mainViewModel.uiState.collectAsStateWithLifecycle()

    val permissions = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
//        Manifest.permission.RECORD_AUDIO
    )

    var permissionsGranted by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        permissionsGranted = permissionsMap.values.all { it }
    }


    LaunchedEffect(key1 = Unit) {
        permissionLauncher.launch(permissions.toTypedArray())
    }

    Column(
        modifier = Modifier
            .background(color = CustomTheme.colors.bg)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {

        MainHeader(
            title = "BeautyU",
            onClickMenu = {
//                SpeechToText().start(context)
                navController.navigate(
                    Screen.MenuScreen.route
                )
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = CustomTheme.colors.bg
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
        )
        {
            item {
                SliderBanner(
                    banners = uiState.value.bannerSliderList,
                    onClick = { data ->
                        navController.navigate(
                            Screen.RecommendSurgeryScreen.route(
                                id = data.id,
                                productName = data.name,
                                productImg = data.resId)
                        )
                    })
            }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp, bottom = 5.dp, start = 10.dp, end = 10.dp),
                    text = "New Beauty",
                    color = CustomTheme.colors.black,
                    style = CustomTheme.typography.title1Bold,
                    textAlign = TextAlign.Center
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
                    text = "Today Hospital",
                    color = CustomTheme.colors.black,
                    style = CustomTheme.typography.title2Regular,
                    textAlign = TextAlign.Center
                )
            }

            item {
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
            }
            item {
                NewBeautyHorizontalList(
                   list = uiState.value.bannerSliderListOld,
                   onClick = { data ->
                       navController.navigate(
                           Screen.DetailScreen.route(
                               id = data.id,
                               )
                       )
                   })
            }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp, bottom = 5.dp, start = 10.dp, end = 10.dp),
                    text = "Hospitals",
                    color = CustomTheme.colors.black,
                    style = CustomTheme.typography.title1Bold,
                    textAlign = TextAlign.Center
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
                    text = "Choose the region you want",
                    color = CustomTheme.colors.black,
                    style = CustomTheme.typography.title2Regular,
                    textAlign = TextAlign.Center
                )
            }

            item {
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
            }

            item {
                RegionChipSelectionSection(
                    locationChipDataList = uiState.value.locationChipDataList,
                    onChipClick = { region ->
                        mainViewModel.setRegion(region)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
            }

            item {
                Text(
                    text = "See All >",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, end = 10.dp)
                        .clickableSingle {
                            navController.navigate(
                                Screen.HospitalListByRegionScreen.route(uiState.value.region)
                            )
                        },
                    color = CustomTheme.colors.black,
                    style = CustomTheme.typography.title3Bold,
                    textAlign = TextAlign.End
                )

            }
            item {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                        .background(colors.bg)
                        .padding(start = 10.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    state = rememberLazyGridState(),
                    columns = GridCells.Fixed(2),
                    content = {
                        items(4) { index ->
                            if (uiState.value.searchingList.size -1 < index) {
                                return@items
                            }
                            val data = uiState.value.searchingList[index]
                            Grid2ItemsByRowCell(
                                id = data.id,
                                resImgId = imageLocalMapperTmpHospital(data.images[0]),
                                descString = data.productName,
                                onClick = { id ->
                                    navController.navigate(
                                        Screen.DetailScreen.route(
                                            id = id,
                                        )
                                    )
                                }
                            )

                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun NewBeautyHorizontalList(
    list: MutableList<ProductData>,
    onClick: (ProductData) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        items(list.size)
        { index ->
            val data = list[index]
            Image(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(150.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .clickableSingle {
                        onClick(data)
                    },
                painter = painterResource(id = imageLocalMapperTmpHospital(data.images[0])),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun RegionChipSelectionSection(
    locationChipDataList: MutableList<LocationChipData>,
    onChipClick: (String) -> Unit
)
{
    FlowRow(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        locationChipDataList.forEach { data ->
            RegionFilterChipContent(
                locationChipData = data,
                onChipClick = {
                    if (!data.isSelected) {
                        onChipClick(data.region)
                    }
                })
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegionFilterChipContent(
    locationChipData: LocationChipData,
    onChipClick: (LocationChipData) -> Unit
) {
    FilterChip(
        selected = locationChipData.isSelected,
        onClick = { onChipClick(locationChipData) },
        label = {
            Text(
                text = locationChipData.region,
                color = CustomTheme.colors.textColorPrimary,
                style = CustomTheme.typography.bodyRegular,
            ) },
        shape = RoundedCornerShape(50),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = if (locationChipData.isSelected) {
                CustomTheme.colors.orange60
            } else {
                CustomTheme.colors.white
            },
            selectedContainerColor = CustomTheme.colors.orange60
        ),
        leadingIcon = {
//            if (locationChipData.isSelected) {
//                Icon(
//                    imageVector = Icons.Filled.LocationOn,
//                    contentDescription = "Localized Description",
//                    modifier = Modifier.size(FilterChipDefaults.IconSize)
//                )
//            } else {
//
//            }
        }
    )
}

