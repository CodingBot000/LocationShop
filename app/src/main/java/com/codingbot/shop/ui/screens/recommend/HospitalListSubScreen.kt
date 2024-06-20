package com.codingbot.shop.ui.screens.recommend

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.ui.component.HospitalInfoList
import com.codingbot.shop.viewmodel.HospitalListSubViewModel


@Composable
fun HospitalListSubScreen(
    navController: NavController,
    id: Int,
    hospitalSubViewModel: HospitalListSubViewModel = hiltViewModel(),
) {
    val logger = remember { Logger("FavoriteScreen", true, "[Screen]") }

    val uiState = hospitalSubViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        hospitalSubViewModel.getHospitalListData(id)
    }

    uiState.value.list?.let { list ->
        HospitalInfoList(list = list)
        { hospitalId ->
            navController.navigate(
                Screen.DetailScreen.route(hospitalId))
        }
    }
}
