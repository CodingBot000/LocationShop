package com.codingbot.shop.data.repository

import com.codingbot.shop.data.datasource.DataSourceFavorite
import com.codingbot.shop.data.datasource.DataSourceProductData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import javax.inject.Inject

class RepositoryProductDataImpl @Inject constructor(
    private val dataSource: DataSourceProductData
): RepositoryProductData {

    override fun getNewBeautyDatas(): List<ProductData> {
        return dataSource.getNewBeautyDatas()
    }

    override fun getProductOriginData(id: Int): ProductData? {
        return dataSource.getProductOriginData(id)
    }

    override fun getDetailDatasOrigin(id: Int): ProductDetailData? {
        return dataSource.getDetailDatasOrigin(id)
    }

    override fun getHospitalListByLocation(currentRegion: String): List<ProductData> {
        return dataSource.getHospitalListByLocation(currentRegion)
    }

    override fun getProductData(id: Int): ProductData? {
        return dataSource.getProductData(id)
    }

    override fun getHospitalDataListBySurgery(surgeryId: Int): List<ProductData> {
        return dataSource.getHospitalDataListBySurgery(surgeryId)
    }

}