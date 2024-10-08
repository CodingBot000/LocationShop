package com.codingbot.shop.data.datasource

import com.codingbot.shop.core.server.DumpServer
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import javax.inject.Inject

class DataSourceProductDataImpl @Inject constructor()
    : DataSourceProductData
{
    override suspend fun getNewBeautyDatas(): List<ProductData> {
        return DumpServer.getNewBeautyDatas()
    }

    override suspend fun getProductOriginData(id: Int): ProductData? {
        return DumpServer.getProductOriginData(id)
    }

    override suspend fun getDetailDatasOrigin(id: Int): ProductDetailData? {
        return DumpServer.getDetailDatasOrigin(id)
    }

    override suspend fun getHospitalListByLocation(currentRegion: String): List<ProductData> {
        return DumpServer.getHospitalListByLocation(currentRegion)
    }

    override suspend fun getProductData(id: Int): ProductData? {
        return DumpServer.getProductData(id)
    }

    override suspend fun getHospitalDataListBySurgery(surgeryId: Int): List<ProductData> {
        return DumpServer.getHospitalDataListBySurgery(surgeryId)
    }

}