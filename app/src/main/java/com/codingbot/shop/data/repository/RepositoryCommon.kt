package com.codingbot.shop.data.repository

import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.SurgeryData

interface RepositoryCommon {
    fun getSurgeryList(): List<SurgeryData>
    fun initLocationChipDataList(): String
    fun getBannerSlideData(): List<HomeBannerData>

    fun getLocationChipDataList(): List<LocationChipData>

    fun setLocationPosition(currentRegion: String): List<LocationChipData>

}