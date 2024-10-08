package com.codingbot.shop.viewmodel

import androidx.lifecycle.viewModelScope
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.data.repository.RepositoryCommon
import com.codingbot.shop.data.repository.RepositoryEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RecommendUiState(
    val eventDataList: List<EventData>? = null,
)

sealed interface RecommendIntent {
    data class EventDataList(val list: List<EventData>?): RecommendIntent

}

@HiltViewModel
class RecommendSurgeryViewModel @Inject constructor(
    private val repositoryEvent: RepositoryEvent,
)
    : BaseViewModel<RecommendUiState, RecommendIntent>(RecommendUiState())
{
    val logger = Logger("DetailViewModel")

    fun getEventData(id: Int) {
        println("eventData getDetailData: $id")
        viewModelScope.launch {
            val eventList = repositoryEvent.getEventDataListById(id)
            execute(RecommendIntent.EventDataList(eventList.toList()))
        }
    }


    override suspend fun RecommendUiState.reduce(intent: RecommendIntent): RecommendUiState =
        when (intent) {
            is RecommendIntent.EventDataList -> {
                copy(eventDataList = intent.list)
            }
        }
}