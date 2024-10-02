package com.codingbot.shop.data.repository

import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData

interface RepositoryProductData {
    suspend fun getNewBeautyDatas() : List<ProductData>
    suspend fun getProductOriginData(id: Int): ProductData?
    suspend fun getDetailDatasOrigin(id: Int): ProductDetailData?
    suspend fun getHospitalListByLocation(currentRegion: String): List<ProductData>
    suspend fun getProductData(id: Int): ProductData?
    suspend fun getHospitalDataListBySurgery(surgeryId: Int): List<ProductData>
}