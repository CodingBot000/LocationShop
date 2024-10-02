package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.repository.RepositoryCommon
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class HospitalSubUiState(
    val list: List<ProductData>? = null
)

sealed interface HospitalSubIntent {
    data class HospitalInfoList(val list: List<ProductData>): HospitalSubIntent
}

@HiltViewModel
class HospitalListSubViewModel @Inject constructor(
    private val repositoryCommon: RepositoryCommon
)
    : BaseViewModel<HospitalSubUiState, HospitalSubIntent>(HospitalSubUiState())
{
    val logger = Logger("HospitalListSubViewModel")

    fun getHospitalListData(id: Int) {
        val list = repositoryCommon.getHospitalDataListBySurgery(id)
        execute(HospitalSubIntent.HospitalInfoList(list))
    }

    override suspend fun HospitalSubUiState.reduce(intent: HospitalSubIntent): HospitalSubUiState =
        when (intent) {
            is HospitalSubIntent.HospitalInfoList -> copy(list = intent.list)
        }
}
