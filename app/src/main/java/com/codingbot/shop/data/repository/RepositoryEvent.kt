package com.codingbot.shop.data.repository

import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.domain.model.ReviewData
import com.codingbot.shop.domain.model.SurgeryData

interface RepositoryEvent {
    suspend fun getEventDataAllList(): List<EventData>
    suspend fun getEventDataListById(id: Int): List<EventData>
    suspend fun getEventDataById(id: Int): EventData?
}