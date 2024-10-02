package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.data.repository.RepositoryCommon
import com.codingbot.shop.data.repository.RepositoryProductData
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
class HospitalListByRegionViewModel @Inject constructor(
    private val repositoryProductData: RepositoryProductData
)
    : BaseViewModel<HospitalListByRegionUiState, HospitalListByRegionIntent>(HospitalListByRegionUiState())
{
    val logger = Logger("HospitalListSubViewModel")

    fun getHospitalListData(currentRegion: String) {
        val dataList = repositoryProductData.getHospitalListByLocation(currentRegion)
        execute(HospitalListByRegionIntent.HospitalInfoList(currentRegion, dataList.toMutableList()))
    }

    override suspend fun HospitalListByRegionUiState.reduce(intent: HospitalListByRegionIntent): HospitalListByRegionUiState =
        when (intent) {
            is HospitalListByRegionIntent.HospitalInfoList -> copy(region = intent.region, list = intent.list)
        }
}
