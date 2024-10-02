package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.repository.RepositoryCommon
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class EventSubUiState(
    val eventDataList: List<EventData>? = null,
)

sealed interface EventSubIntent {
    data class EventDataList(val list: List<EventData>?): EventSubIntent
}

@HiltViewModel
class EventSubViewModel @Inject constructor(
    private val repositoryCommon: RepositoryCommon
)
    : BaseViewModel<EventSubUiState, EventSubIntent>(EventSubUiState())
{
    val logger = Logger("DetailViewModel")
    fun getEventData(id: Int) {
        println("getDetailData: $id")
        val eventList = repositoryCommon.getEventDataListById(id)
        execute(EventSubIntent.EventDataList(eventList.toList()))
    }

    override suspend fun EventSubUiState.reduce(intent: EventSubIntent): EventSubUiState =
        when (intent) {
            is EventSubIntent.EventDataList -> {
                copy(eventDataList = intent.list)
            }
        }
}