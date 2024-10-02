package com.codingbot.shop.data.datasource

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


    override fun getLocationChipDataList(): List<LocationChipData> {
        return DumpServer.getLocationChipDataList()
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

}