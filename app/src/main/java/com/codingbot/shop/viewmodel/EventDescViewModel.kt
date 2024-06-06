package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.server.DumpServer.eventDataList
import com.codingbot.shop.core.server.DumpServer.productDatasOrigin
import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.ProductData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class EventUiState(
    val detailData: EventData? = null,
    val productData: ProductData? = null
)

sealed interface EventIntent {

    data class DetailData(val detailData: EventData): EventIntent
    data class HospitalInfo(val productData: ProductData): EventIntent
}

@HiltViewModel
class EventDescViewModel @Inject constructor()
    : BaseViewModel<EventUiState, EventIntent>(EventUiState())
{
    val logger = Logger("EventDescViewModel")

    fun getEventData(id: Int) {
        eventDataList?.let { eventList ->
            eventList.find { eventData -> eventData.id == id }?.let { eventData ->
                execute(EventIntent.DetailData(eventData))
            }
            productDatasOrigin?.let { productList ->
                productList.find { productData -> productData.id == id }
                    ?.let { productData ->
                      execute(EventIntent.HospitalInfo(productData))
                    }
             }
        }


    }

    override suspend fun EventUiState.reduce(intent: EventIntent): EventUiState =
        when (intent) {
            is EventIntent.DetailData -> copy(detailData = intent.detailData)
            is EventIntent.HospitalInfo -> copy(productData = intent.productData)
        }
}

// 잠깐 주소만 처리하는