package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.domain.model.ProductDetailDescData
import com.codingbot.shop.repository.RepositoryCommon
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val repositoryCommon: RepositoryCommon
)
    : BaseViewModel<HospitalInfoSubUiState, HospitalInfoSubIntent>(HospitalInfoSubUiState())
{
    val logger = Logger("HospitalInfoSubViewModel")

    fun getDetailData(id: Int) {
        println("getDetailData: $id")
        val productData = repositoryCommon.getProductOriginData(id)
        val detailData = repositoryCommon.getDetailDatasOrigin(id)

        if (productData == null || detailData == null)
            return

        execute(HospitalInfoSubIntent.DetailData(productData, detailData))
    }

    override suspend fun HospitalInfoSubUiState.reduce(intent: HospitalInfoSubIntent): HospitalInfoSubUiState =
        when (intent) {
            is HospitalInfoSubIntent.DetailData -> {
                copy(productData = intent.productData,  detailData = intent.detailData)
            }
        }
}