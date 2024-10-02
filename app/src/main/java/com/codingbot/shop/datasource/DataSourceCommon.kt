package com.codingbot.shop.datasource

import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.domain.model.ReviewData
import com.codingbot.shop.domain.model.SurgeryData

interface DataSourceCommon {
    fun getSurgeryList(): List<SurgeryData>
    fun initLocationChipDataList(): String
    fun getBannerSlideData(): List<HomeBannerData>
    fun getNewBeautyDatas() : List<ProductData>

    fun getProductOriginData(id: Int): ProductData?
    fun getDetailDatasOrigin(id: Int): ProductDetailData?

    fun getHospitalListByLocation(currentRegion: String): List<ProductData>

    fun getLocationChipDataList(): List<LocationChipData>

    fun getEventDataAllList(): List<EventData>

    fun getEventDataListById(id: Int): List<EventData>

    fun getEventDataById(id: Int): EventData?

    fun getProductData(id: Int): ProductData?
    fun getReviewDataListBySurgery(surgeryId: Int): List<ReviewData>
    fun getHospitalDataListBySurgery(surgeryId: Int): List<ProductData>
    fun setLocationPosition(currentRegion: String): List<LocationChipData>

}