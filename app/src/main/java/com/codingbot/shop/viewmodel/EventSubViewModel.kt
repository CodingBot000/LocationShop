package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.server.DumpServer
import com.codingbot.shop.domain.model.EventData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class EventSubUiState(
    val eventDataList: List<EventData>? = null,
)

sealed interface EventSubIntent {
    data class EventDataList(val list: List<EventData>?): EventSubIntent
}

@HiltViewModel
class EventSubViewModel @Inject constructor()
    : BaseViewModel<EventSubUiState, EventSubIntent>(EventSubUiState())
{
    val logger = Logger("DetailViewModel")
    fun getEventData(id: Int) {
        println("getDetailData: $id")
        val eventList = mutableListOf<EventData>()
        DumpServer.eventDataList?.forEach { eventData ->
            eventData.surgeryIds.find {
                    surgeryId -> surgeryId == id
            }?.let {
                eventList.add(eventData)
            }
        }
        execute(EventSubIntent.EventDataList(eventList.toList()))
    }

    override suspend fun EventSubUiState.reduce(intent: EventSubIntent): EventSubUiState =
        when (intent) {
            is EventSubIntent.EventDataList -> {
                copy(eventDataList = intent.list)
            }
        }
}