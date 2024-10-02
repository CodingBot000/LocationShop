package com.codingbot.shop.data.datasource

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

    fun getLocationChipDataList(): List<LocationChipData>

    fun setLocationPosition(currentRegion: String): List<LocationChipData>

}