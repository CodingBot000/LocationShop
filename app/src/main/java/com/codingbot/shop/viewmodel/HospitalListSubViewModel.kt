package com.codingbot.shop.viewmodel

import androidx.lifecycle.viewModelScope
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.data.repository.RepositoryCommon
import com.codingbot.shop.data.repository.RepositoryProductData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HospitalSubUiState(
    val list: List<ProductData>? = null
)

sealed interface HospitalSubIntent {
    data class HospitalInfoList(val list: List<ProductData>): HospitalSubIntent
}

@HiltViewModel
class HospitalListSubViewModel @Inject constructor(
    private val repositoryProductData: RepositoryProductData
)
    : BaseViewModel<HospitalSubUiState, HospitalSubIntent>(HospitalSubUiState())
{
    val logger = Logger("HospitalListSubViewModel")

    fun getHospitalListData(id: Int) {
        viewModelScope.launch {
            val list = repositoryProductData.getHospitalDataListBySurgery(id)
            execute(HospitalSubIntent.HospitalInfoList(list))
        }
    }

    override suspend fun HospitalSubUiState.reduce(intent: HospitalSubIntent): HospitalSubUiState =
        when (intent) {
            is HospitalSubIntent.HospitalInfoList -> copy(list = intent.list)
        }
}
