package com.codingbot.shop.repository

import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.domain.model.ReviewData
import com.codingbot.shop.domain.model.SurgeryData

interface RepositoryCommon {
    fun getSurgeryList(): List<SurgeryData>
    fun initLocationChipDataList(): String
    fun getBannerSlideData(): List<HomeBannerData>
    fun getNewBeautyDatas() : List<ProductData>

    fun getProductOriginData(id: Int): ProductData?
    fun getDetailDatasOrigin(id: Int): ProductDetailData?

    fun getFavoriteStoredDatas(): List<ProductData>
    fun getFavoriteStoredData(id: Int): ProductData?
    fun addFavoriteStoredData(id: Int)
    fun addFavoriteStoredData(productData: ProductData)
    fun getHospitalListByLocation(currentRegion: String): List<ProductData>

    fun getLocationChipDataList(): List<LocationChipData>

    fun getEventDataAllList(): List<EventData>

    fun getEventDataListById(id: Int): List<EventData>

    fun getEventDataById(id: Int): EventData?


    fun removeFavoriteStoredData(id: Int): Boolean

    fun getProductData(id: Int): ProductData?
    fun getReviewDataListBySurgery(surgeryId: Int): List<ReviewData>
    fun getHospitalDataListBySurgery(surgeryId: Int): List<ProductData>


    fun setLocationPosition(currentRegion: String): List<LocationChipData>

    fun test(testData: Int): Int
}