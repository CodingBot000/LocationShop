package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.server.DumpServer
import com.codingbot.shop.domain.model.ProductData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class HospitalListByRegionUiState(
    val list: List<ProductData>? = null,
    val region: String = ""
)

sealed interface HospitalListByRegionIntent {
    data class HospitalInfoList(val region: String, val list: List<ProductData>): HospitalListByRegionIntent
}

@HiltViewModel
class HospitalListByRegionViewModel @Inject constructor()
    : BaseViewModel<HospitalListByRegionUiState, HospitalListByRegionIntent>(HospitalListByRegionUiState())
{
    val logger = Logger("HospitalListSubViewModel")

    fun getHospitalListData(currentRegion: String) {
        val dataList = DumpServer.getHospitalListByLocation(currentRegion)
        execute(HospitalListByRegionIntent.HospitalInfoList(currentRegion, dataList.toMutableList()))
    }

    override suspend fun HospitalListByRegionUiState.reduce(intent: HospitalListByRegionIntent): HospitalListByRegionUiState =
        when (intent) {
            is HospitalListByRegionIntent.HospitalInfoList -> copy(region = intent.region, list = intent.list)
        }
}
