package com.codingbot.shop.data.repository

import com.codingbot.shop.data.datasource.DataSourceCommon
import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.SurgeryData
import javax.inject.Inject

class RepositoryCommonImpl @Inject constructor(
    private val dataSource: DataSourceCommon
): RepositoryCommon {
    override fun getSurgeryList(): List<SurgeryData> {
        return dataSource.getSurgeryList()
    }

    override fun getLocationChipDataList(): List<LocationChipData> {
        return dataSource.getLocationChipDataList()
    }

    override fun setLocationPosition(currentRegion: String): List<LocationChipData> {
        return dataSource.setLocationPosition(currentRegion)
    }

    override fun initLocationChipDataList(): String {
        return dataSource.initLocationChipDataList()
    }

    override fun getBannerSlideData(): List<HomeBannerData> {
        return dataSource.getBannerSlideData()
    }
}