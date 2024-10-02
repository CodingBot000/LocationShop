package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.data.repository.RepositoryCommon
import com.codingbot.shop.data.repository.RepositoryEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class EventMenuUiState(
    val eventDataList: List<EventData>? = null,
)

sealed interface EventMenuIntent {
    data class EventDataList(val list: List<EventData>?): EventMenuIntent
}

@HiltViewModel
class EventMenuViewModel @Inject constructor(
    private val repositoryEvent: RepositoryEvent,
)
    : BaseViewModel<EventMenuUiState, EventMenuIntent>(EventMenuUiState())
{
    val logger = Logger("EventMenuViewModel")

    fun getAllEventData() {
        execute(EventMenuIntent.EventDataList(
            repositoryEvent.getEventDataAllList().toMutableList()
        ))
    }

    override suspend fun EventMenuUiState.reduce(intent: EventMenuIntent): EventMenuUiState =
        when (intent) {
            is EventMenuIntent.EventDataList -> {
                copy(eventDataList = intent.list)
            }
        }
}