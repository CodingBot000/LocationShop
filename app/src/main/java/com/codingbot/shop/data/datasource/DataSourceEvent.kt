package com.codingbot.shop.data.datasource

import com.codingbot.shop.core.server.DumpServer
import com.codingbot.shop.domain.model.EventData

interface DataSourceEvent {
    suspend fun getEventDataById(id: Int): EventData?
    suspend fun getEventDataAllList(): List<EventData>
    suspend fun getEventDataListById(id: Int): List<EventData>
}