package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.server.DumpServer
import com.codingbot.shop.domain.model.EventData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class RecommendUiState(
    val eventDataList: List<EventData>? = null,
)

sealed interface RecommendIntent {
    data class EventDataList(val list: List<EventData>?): RecommendIntent

}

@HiltViewModel
class RecommendSurgeryViewModel @Inject constructor()
    : BaseViewModel<RecommendUiState, RecommendIntent>(RecommendUiState())
{
    val logger = Logger("DetailViewModel")

    init {

    }
    fun getEventData(id: Int) {
        println("eventData getDetailData: $id")
        val eventList = mutableListOf<EventData>()
        DumpServer.eventDataList?.forEach { eventData ->
            println("eventData:${eventData.id} eventData.surgeryIds:${eventData.surgeryIds.joinToString()}")
            eventData.surgeryIds.find {
                    surgeryId -> surgeryId == id
            }?.let {
                eventList.add(eventData)
            }
        }
        execute(RecommendIntent.EventDataList(eventList.toList()))

    }


    override suspend fun RecommendUiState.reduce(intent: RecommendIntent): RecommendUiState =
        when (intent) {
            is RecommendIntent.EventDataList -> {
                copy(eventDataList = intent.list)
            }
        }
}