package com.codingbot.shop.ui.screens.main

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import com.codingbot.shop.core.common.imageLocalMapperTmpHospital
import com.codingbot.shop.ui.component.DetailHeader
import com.codingbot.shop.ui.component.HospitalInfoCell
import com.codingbot.shop.ui.component.HospitalInfoList
import com.codingbot.shop.ui.theme.CustomTheme
import com.codingbot.shop.viewmodel.HospitalListByRegionViewModel
import com.codingbot.shop.viewmodel.HospitalListSubViewModel


@Composable
fun HospitalListByRegionScreen(
    navController: NavController,
    region: String,
    context: Context = LocalContext.current,
    hospitalListByRegionViewModel: HospitalListByRegionViewModel = hiltViewModel(),
) {
    val logger = remember { Logger("HospitalListByRegionScreen", true, "[Screen]") }

    val uiState = hospitalListByRegionViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        hospitalListByRegionViewModel.getHospitalListData(region)
    }

    Column(
        modifier = Modifier
            .background(color = CustomTheme.colors.bg)
            .fillMaxSize(),
    )
    {
        DetailHeader(
            title = "$region Hospitals",
            onClickBack = {
                navController.popBackStack()
            }
        )
        uiState.value.list?.let { list ->
            HospitalInfoList(list = list)
            { hospitalId ->
                navController.navigate(
                    Screen.DetailScreen.route(hospitalId))
            }
        }
    }
}
