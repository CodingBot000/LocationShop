package com.codingbot.shop.data.repository

import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.SurgeryData

interface RepositoryCommon {
    suspend fun getSurgeryList(): List<SurgeryData>
    suspend fun initLocationChipDataList(): String
    suspend fun getBannerSlideData(): List<HomeBannerData>

    suspend fun getLocationChipDataList(): List<LocationChipData>

    suspend fun setLocationPosition(currentRegion: String): List<LocationChipData>

}