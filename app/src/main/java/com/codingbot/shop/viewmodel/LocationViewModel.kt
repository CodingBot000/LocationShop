package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.server.InitValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class LocationUiState(
    val detailData: List<String> = mutableListOf()
)

sealed interface LocationIntent {

    data class DetailData(val list: List<String>): LocationIntent
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
                execute(LocationIntent.DetailData(InitValue.LocationsData.getHospitalList(it)))
            }
    }

    override suspend fun LocationUiState.reduce(intent: LocationIntent): LocationUiState =
        when (intent) {
            is LocationIntent.DetailData -> copy(detailData = intent.list)

        }
}
