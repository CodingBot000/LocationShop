package com.codingbot.shop.data.repository

import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData

interface RepositoryProductData {
    fun getNewBeautyDatas() : List<ProductData>
    fun getProductOriginData(id: Int): ProductData?
    fun getDetailDatasOrigin(id: Int): ProductDetailData?
    fun getHospitalListByLocation(currentRegion: String): List<ProductData>
    fun getProductData(id: Int): ProductData?
    fun getHospitalDataListBySurgery(surgeryId: Int): List<ProductData>
}