package com.codingbot.shop.viewmodel

import androidx.lifecycle.viewModelScope
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.domain.model.ProductDetailDescData
import com.codingbot.shop.data.repository.RepositoryCommon
import com.codingbot.shop.data.repository.RepositoryProductData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HospitalInfoSubUiState(
    val productData: ProductData? = null,
    val detailData: ProductDetailData? = null,
    val detailDescData: ProductDetailDescData? = null,
)

sealed interface HospitalInfoSubIntent {
    data class DetailData(val productData: ProductData?, val detailData: ProductDetailData?): HospitalInfoSubIntent
}

@HiltViewModel
class HospitalInfoSubViewModel @Inject constructor(
    private val repositoryProductData: RepositoryProductData
)
    : BaseViewModel<HospitalInfoSubUiState, HospitalInfoSubIntent>(HospitalInfoSubUiState())
{
    val logger = Logger("HospitalInfoSubViewModel")

    fun getDetailData(id: Int) {
        println("getDetailData: $id")
        viewModelScope.launch {
            val productData = repositoryProductData.getProductOriginData(id)
            val detailData = repositoryProductData.getDetailDatasOrigin(id)

            if (productData == null || detailData == null)
                return@launch

            execute(HospitalInfoSubIntent.DetailData(productData, detailData))
        }

    }

    override suspend fun HospitalInfoSubUiState.reduce(intent: HospitalInfoSubIntent): HospitalInfoSubUiState =
        when (intent) {
            is HospitalInfoSubIntent.DetailData -> {
                copy(productData = intent.productData,  detailData = intent.detailData)
            }
        }
}