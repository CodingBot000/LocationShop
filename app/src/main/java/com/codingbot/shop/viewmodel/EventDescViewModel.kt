package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.server.DumpServer
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

                productDatasOrigin?.let { productList ->
                    productList.find { productData -> productData.id == eventData.hospital_id }
                        ?.let { productData ->
                          execute(EventIntent.HospitalInfo(productData))
                        }
                 }
            }
        }
    }

    fun getSurgeryNames(surgeryIds: List<Int>): List<String> {
//        val sb = StringBuilder()
        val surgeryNames = mutableListOf<String>()
        surgeryIds.forEach { surgeryId ->
            DumpServer.surgeryDataList?.let { surgeryDataList ->
//                sb.append("${list[surgeryId].surgeryName} ")
                surgeryDataList.find { data -> data.id == surgeryId }?.let {
                    surgeryNames.add(it.surgeryName)
                }
//                surgeryNames.add(list[surgeryId].surgeryName)
            }
        }
        return surgeryNames
//        return sb.toString()
    }

    override suspend fun EventUiState.reduce(intent: EventIntent): EventUiState =
        when (intent) {
            is EventIntent.DetailData -> copy(detailData = intent.detailData)
            is EventIntent.HospitalInfo -> copy(productData = intent.productData)
        }
}

// 잠깐 주소만 처리하는