package com.codingbot.shop.data.datasource

import com.codingbot.shop.core.server.DumpServer
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import javax.inject.Inject

class DataSourceProductDataImpl @Inject constructor()
    : DataSourceProductData
{
    override fun getNewBeautyDatas(): List<ProductData> {
        return DumpServer.getNewBeautyDatas()
    }

    override fun getProductOriginData(id: Int): ProductData? {
        return DumpServer.getProductOriginData(id)
    }

    override fun getDetailDatasOrigin(id: Int): ProductDetailData? {
        return DumpServer.getDetailDatasOrigin(id)
    }

    override fun getHospitalListByLocation(currentRegion: String): List<ProductData> {
        return DumpServer.getHospitalListByLocation(currentRegion)
    }

    override fun getProductData(id: Int): ProductData? {
        return DumpServer.getProductData(id)
    }

    override fun getHospitalDataListBySurgery(surgeryId: Int): List<ProductData> {
        return DumpServer.getHospitalDataListBySurgery(surgeryId)
    }

}