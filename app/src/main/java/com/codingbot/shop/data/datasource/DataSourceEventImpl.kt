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

class DataSourceEventImpl @Inject constructor(): DataSourceEvent {

    override suspend fun getEventDataAllList(): List<EventData> {
        return DumpServer.getEventDataAllList()
    }

    override suspend fun getEventDataListById(id: Int): List<EventData> {
        return DumpServer.getEventDataListById(id)
    }

    override suspend fun getEventDataById(id: Int): EventData? {
        return DumpServer.getEventDataById(id)
    }
}