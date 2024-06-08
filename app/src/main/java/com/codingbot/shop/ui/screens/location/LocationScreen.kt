package com.codingbot.shop.ui.screens.location

import MapView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.ui.component.DetailHeader
import com.codingbot.shop.ui.component.HospitalInfoList
import com.codingbot.shop.ui.theme.CustomTheme
import com.codingbot.shop.viewmodel.LocationViewModel
import kotlinx.coroutines.delay


@Composable
fun LocationScreen(
    navController: NavController,
    locationNameString: String,
    locationViewModel: LocationViewModel = hiltViewModel()
) {
    val logger = remember { Logger("LocationScreen", true, "[Screen]") }
//
    val uiState = locationViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        delay(500)
        locationViewModel.getLocationProductList(locationNameString)
        locationViewModel.getHospitalListDataByRegion(locationNameString)
    }
    Column(
        modifier = Modifier
            .background(color = CustomTheme.colors.bg)
            .fillMaxSize(),

    )
    {
        DetailHeader(
            title = locationNameString,
            onClickBack = {
                navController.popBackStack()
            }
        )


        Spacer(modifier = Modifier.padding(bottom = 10.dp))


        uiState.value.mapMarkingData?.let {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(shape = RoundedCornerShape(15.dp))
                .padding(horizontal = 10.dp)) {
                MapView(it)

            }
        }
        Spacer(modifier = Modifier.padding(bottom = 10.dp))

        uiState.value.hospitalList?.let { list ->
            HospitalInfoList(list = list)
            { hospitalId ->
                navController.navigate(
                    Screen.DetailScreen.route(hospitalId))
            }
        }
    }
}
