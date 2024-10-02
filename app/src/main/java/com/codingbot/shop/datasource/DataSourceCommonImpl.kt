package com.codingbot.shop.datasource

import com.codingbot.shop.core.server.DumpServer
import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.domain.model.ReviewData
import com.codingbot.shop.domain.model.SurgeryData
import javax.inject.Inject

class DataSourceCommonImpl @Inject constructor(): DataSourceCommon {
    override fun getSurgeryList(): List<SurgeryData> {
        return DumpServer.getSurgeryList() ?: emptyList()
    }

    override fun getHospitalListByLocation(currentRegion: String): List<ProductData> {
        return DumpServer.getHospitalListByLocation(currentRegion)
    }

    override fun getLocationChipDataList(): List<LocationChipData> {
        return DumpServer.getLocationChipDataList()
    }

    override fun getEventDataAllList(): List<EventData> {
        return DumpServer.getEventDataAllList()
    }

    override fun getEventDataListById(id: Int): List<EventData> {
        return DumpServer.getEventDataListById(id)
    }

    override fun getEventDataById(id: Int): EventData? {
        return DumpServer.getEventDataById(id)
    }



    override fun getProductData(id: Int): ProductData? {
        return DumpServer.getProductData(id)
    }

    override fun getReviewDataListBySurgery(surgeryId: Int): List<ReviewData> {
        return DumpServer.getReviewDataListBySurgery(surgeryId)
    }

    override fun getHospitalDataListBySurgery(surgeryId: Int): List<ProductData> {
        return DumpServer.getHospitalDataListBySurgery(surgeryId)
    }

    override fun setLocationPosition(currentRegion: String): List<LocationChipData> {
        return DumpServer.setLocationPosition(currentRegion)
    }

    override fun initLocationChipDataList(): String {
        return DumpServer.initLocationChipDataList()
    }

    override fun getBannerSlideData(): List<HomeBannerData> {
        return DumpServer.getBannerSlideData()
    }

    override fun getNewBeautyDatas(): List<ProductData> {
        return DumpServer.getNewBeautyDatas()
    }

    override fun getProductOriginData(id: Int): ProductData? {
        return DumpServer.getProductOriginData(id)
    }

    override fun getDetailDatasOrigin(id: Int): ProductDetailData? {
        return DumpServer.getDetailDatasOrigin(id)
    }
}