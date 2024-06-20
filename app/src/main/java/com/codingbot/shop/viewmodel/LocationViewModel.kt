package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.server.DumpServer
import com.codingbot.shop.core.server.InitValue
import com.codingbot.shop.domain.model.ProductData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class LocationUiState(
    val mapMarkingData: List<String> = mutableListOf(),
    val hospitalList: List<ProductData>? = null
)

sealed interface LocationIntent {
    data class MapMarkingData(val list: List<String>): LocationIntent
    data class HospitalInfoList(val list: List<ProductData>): LocationIntent
}

@HiltViewModel
class LocationViewModel @Inject constructor()
    : BaseViewModel<LocationUiState, LocationIntent>(LocationUiState())
{
    val logger = Logger("LocationViewModel")

    fun getLocationProductList(locationNameString: String) {
        InitValue.LocationNames.values()
            .find { it -> it.name == locationNameString }
            ?.let {
                execute(LocationIntent.MapMarkingData(InitValue.LocationsData.getHospitalList(it)))
            }
    }

    fun getHospitalListDataByRegion(currentRegion: String) {
        val list = DumpServer.productDatasOrigin
            ?.filter { productData -> productData.region == currentRegion }
            ?.toMutableList() ?: mutableListOf()

        execute(LocationIntent.HospitalInfoList(list))
    }

    override suspend fun LocationUiState.reduce(intent: LocationIntent): LocationUiState =
        when (intent) {
            is LocationIntent.MapMarkingData -> copy(mapMarkingData = intent.list)
            is LocationIntent.HospitalInfoList -> copy(hospitalList = intent.list)
        }
}
