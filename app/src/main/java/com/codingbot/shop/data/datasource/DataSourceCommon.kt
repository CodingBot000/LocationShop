package com.codingbot.shop.data.datasource

import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.domain.model.ReviewData
import com.codingbot.shop.domain.model.SurgeryData

interface DataSourceCommon {
    suspend fun getSurgeryList(): List<SurgeryData>
    suspend fun initLocationChipDataList(): String
    suspend fun getBannerSlideData(): List<HomeBannerData>

    suspend fun getLocationChipDataList(): List<LocationChipData>

    suspend fun setLocationPosition(currentRegion: String): List<LocationChipData>

}