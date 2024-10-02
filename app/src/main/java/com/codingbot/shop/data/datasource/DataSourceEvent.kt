package com.codingbot.shop.data.datasource

import com.codingbot.shop.core.server.DumpServer
import com.codingbot.shop.domain.model.EventData

interface DataSourceEvent {
    fun getEventDataById(id: Int): EventData?
    fun getEventDataAllList(): List<EventData>
    fun getEventDataListById(id: Int): List<EventData>
}