package com.codingbot.shop.data.repository

import com.codingbot.shop.data.datasource.DataSourceFavorite
import com.codingbot.shop.data.datasource.DataSourceProductData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import javax.inject.Inject

class RepositoryProductDataImpl @Inject constructor(
    private val dataSource: DataSourceProductData
): RepositoryProductData {

    override suspend fun getNewBeautyDatas(): List<ProductData> {
        return dataSource.getNewBeautyDatas()
    }

    override suspend fun getProductOriginData(id: Int): ProductData? {
        return dataSource.getProductOriginData(id)
    }

    override suspend fun getDetailDatasOrigin(id: Int): ProductDetailData? {
        return dataSource.getDetailDatasOrigin(id)
    }

    override suspend fun getHospitalListByLocation(currentRegion: String): List<ProductData> {
        return dataSource.getHospitalListByLocation(currentRegion)
    }

    override suspend fun getProductData(id: Int): ProductData? {
        return dataSource.getProductData(id)
    }

    override suspend fun getHospitalDataListBySurgery(surgeryId: Int): List<ProductData> {
        return dataSource.getHospitalDataListBySurgery(surgeryId)
    }

}