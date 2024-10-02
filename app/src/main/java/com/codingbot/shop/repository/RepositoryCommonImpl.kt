package com.codingbot.shop.repository

import com.codingbot.shop.datasource.DataSourceCommon
import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.domain.model.ReviewData
import com.codingbot.shop.domain.model.SurgeryData
import javax.inject.Inject

class RepositoryCommonImpl @Inject constructor(
    val dataSourceCommon: DataSourceCommon
): RepositoryCommon {
    override fun getSurgeryList(): List<SurgeryData> {
        return dataSourceCommon.getSurgeryList() ?: emptyList()
    }

    override fun getHospitalListByLocation(currentRegion: String): List<ProductData> {
        return dataSourceCommon.getHospitalListByLocation(currentRegion)
    }

    override fun getLocationChipDataList(): List<LocationChipData> {
        return dataSourceCommon.getLocationChipDataList()
    }

    override fun getEventDataAllList(): List<EventData> {
        return dataSourceCommon.getEventDataAllList()
    }

    override fun getEventDataListById(id: Int): List<EventData> {
        return dataSourceCommon.getEventDataListById(id)
    }

    override fun getEventDataById(id: Int): EventData? {
        return dataSourceCommon.getEventDataById(id)
    }

    override fun getProductData(id: Int): ProductData? {
        return dataSourceCommon.getProductData(id)
    }

    override fun getReviewDataListBySurgery(surgeryId: Int): List<ReviewData> {
        return dataSourceCommon.getReviewDataListBySurgery(surgeryId)
    }

    override fun getHospitalDataListBySurgery(surgeryId: Int): List<ProductData> {
        return dataSourceCommon.getHospitalDataListBySurgery(surgeryId)
    }

    override fun setLocationPosition(currentRegion: String): List<LocationChipData> {
        return dataSourceCommon.setLocationPosition(currentRegion)
    }

    override fun initLocationChipDataList(): String {
        return dataSourceCommon.initLocationChipDataList()
    }

    override fun getBannerSlideData(): List<HomeBannerData> {
        return dataSourceCommon.getBannerSlideData()
    }

    override fun getNewBeautyDatas(): List<ProductData> {
        return dataSourceCommon.getNewBeautyDatas()
    }

    override fun getProductOriginData(id: Int): ProductData? {
        return dataSourceCommon.getProductOriginData(id)
    }

    override fun getDetailDatasOrigin(id: Int): ProductDetailData? {
        return dataSourceCommon.getDetailDatasOrigin(id)
    }

    override fun test(testData: Int): Int {
        return testData+ 3
    }
}