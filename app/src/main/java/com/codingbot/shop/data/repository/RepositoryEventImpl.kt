package com.codingbot.shop.data.repository

import com.codingbot.shop.data.datasource.DataSourceEvent
import com.codingbot.shop.domain.model.EventData
import javax.inject.Inject

class RepositoryEventImpl @Inject constructor(
    private val dataSource: DataSourceEvent
): RepositoryEvent {

    override fun getEventDataAllList(): List<EventData> {
        return dataSource.getEventDataAllList()
    }

    override fun getEventDataListById(id: Int): List<EventData> {
        return dataSource.getEventDataListById(id)
    }

    override fun getEventDataById(id: Int): EventData? {
        return dataSource.getEventDataById(id)
    }
}